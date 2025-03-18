package hsm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.*;

class BloodBankWindow extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addBloodButton, showAllDataButton, takeBloodButton, backButton;
    private static final String FILE_PATH = "blood_data.txt";

    public BloodBankWindow() {
        setTitle("Blood Bank Management");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Column headers
        String[] columns = {"Blood Type", "Available Units"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Table properties
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        addBloodButton = new JButton("Add Blood");
        showAllDataButton = new JButton("Show All Data");
        takeBloodButton = new JButton("Take Blood");
        backButton = new JButton("Back");

        styleButton(addBloodButton, new Color(50, 205, 50));   // Green
        styleButton(showAllDataButton, new Color(70, 130, 180)); // Steel Blue
        styleButton(takeBloodButton, new Color(255, 140, 0));  // Dark Orange
        styleButton(backButton, new Color(220, 20, 60));       // Crimson

        buttonPanel.add(addBloodButton);
        buttonPanel.add(showAllDataButton);
        buttonPanel.add(takeBloodButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addBloodButton.addActionListener(e -> addBlood());
        showAllDataButton.addActionListener(e -> loadBloodData());
        takeBloodButton.addActionListener(e -> takeBlood());
        backButton.addActionListener(e -> {
            dispose();
            new Reception();
        });

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to add or update blood entry
    private void addBlood() {
        String bloodType = JOptionPane.showInputDialog("Enter Blood Type:");
        String unitsStr = JOptionPane.showInputDialog("Enter Available Units:");

        if (bloodType != null && unitsStr != null) {
            try {
                int newUnits = Integer.parseInt(unitsStr);
                bloodType = bloodType.toUpperCase();  // Normalize blood type (case-insensitive)
                Map<String, Integer> bloodData = readBloodData();

                // If blood type exists, update the amount
                bloodData.put(bloodType, bloodData.getOrDefault(bloodType, 0) + newUnits);

                writeBloodData(bloodData); // Save to file
                JOptionPane.showMessageDialog(this, "Blood record updated successfully!");
                loadBloodData();  // Refresh table
            } catch (IOException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error saving data.");
            }
        }
    }

    // Method to load blood data from file
    private void loadBloodData() {
        tableModel.setRowCount(0); // Clear existing data
        try {
            Map<String, Integer> bloodData = readBloodData();
            for (Map.Entry<String, Integer> entry : bloodData.entrySet()) {
                tableModel.addRow(new String[]{entry.getKey(), entry.getValue().toString()});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data.");
        }
    }

    // Method to take blood (deduct units)
    private void takeBlood() {
        String bloodType = JOptionPane.showInputDialog("Enter Blood Type to Take Blood From:");
        String unitsStr = JOptionPane.showInputDialog("Enter Units to Deduct:");

        if (bloodType != null && unitsStr != null) {
            try {
                int unitsToDeduct = Integer.parseInt(unitsStr);
                bloodType = bloodType.toUpperCase();  // Normalize blood type
                Map<String, Integer> bloodData = readBloodData();

                if (bloodData.containsKey(bloodType) && bloodData.get(bloodType) >= unitsToDeduct) {
                    bloodData.put(bloodType, bloodData.get(bloodType) - unitsToDeduct);
                    writeBloodData(bloodData); // Save updated data
                    JOptionPane.showMessageDialog(this, "Blood taken successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough blood available or blood type not found.");
                }

                loadBloodData(); // Refresh table
            } catch (IOException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error processing request.");
            }
        }
    }

    // Helper method to read blood data from file into a Map
    private Map<String, Integer> readBloodData() throws IOException {
        Map<String, Integer> bloodData = new HashMap<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) file.createNewFile();  // Ensure file exists

        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] record = line.split(",");
            if (record.length == 2) {
                bloodData.put(record[0].toUpperCase(), Integer.parseInt(record[1]));  // Normalize case
            }
        }
        reader.close();
        return bloodData;
    }

    // Helper method to write blood data from a Map to a file
    private void writeBloodData(Map<String, Integer> bloodData) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Map.Entry<String, Integer> entry : bloodData.entrySet()) {
            writer.write(entry.getKey() + "," + entry.getValue());
            writer.newLine();
        }
        writer.close();
    }

    // Helper function to style buttons
    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
    }

    public static void main(String[] args) {
        new BloodBankWindow();
    }
}
