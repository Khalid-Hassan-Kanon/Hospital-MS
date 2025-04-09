package hsm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AmbulanceWindow extends JFrame {

    private JTable ambulanceTable;
    private DefaultTableModel model;
    private JButton addButton, removeButton, displayButton, backButton;
    private JTextField ambulanceIDField, driverNameField, contactField;
    private static final String FILE_NAME = "ambulances.txt";

    public AmbulanceWindow() {
        setTitle("Hospital Ambulance Management");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(60, 130, 150));
        JLabel titleLabel = new JLabel("Ambulance Management");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);

        // Center Panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(230, 230, 230));

        // Form
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(new Color(230, 230, 230));
        ambulanceIDField = new JTextField();
        driverNameField = new JTextField();
        contactField = new JTextField();
        formPanel.add(new JLabel("Ambulance ID:"));
        formPanel.add(ambulanceIDField);
        formPanel.add(new JLabel("Driver Name:"));
        formPanel.add(driverNameField);
        formPanel.add(new JLabel("Contact No:"));
        formPanel.add(contactField);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(230, 230, 230));
        addButton = new JButton("Add Info");
        removeButton = new JButton("Remove Info");

        styleButton(addButton, new Color(40, 167, 69));
        styleButton(removeButton, new Color(220, 53, 69));

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // Table
        model = new DefaultTableModel(new String[]{"Ambulance ID", "Driver Name", "Contact No"}, 0);
        ambulanceTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(ambulanceTable);

        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        // Back button panel
        JPanel backPanel = new JPanel();
        backPanel.setBackground(new Color(60, 130, 150));
        backButton = new JButton("Back");
        backButton.setBackground(new Color(244, 67, 54));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(150, 40));
        backPanel.add(backButton);

        // Add listeners
        addButton.addActionListener(e -> addAmbulance());
        removeButton.addActionListener(e -> removeAmbulance());
        backButton.addActionListener(e -> {
            setVisible(false);
            new Reception();
        });

        // Add to frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.SOUTH);

        loadAmbulanceData();
        setVisible(true);
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 35));
        button.setFocusPainted(false);
    }

    private void addAmbulance() {
        String id = ambulanceIDField.getText();
        String name = driverNameField.getText();
        String contact = contactField.getText();

        if (id.isEmpty() || name.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            model.addRow(new Object[]{id, name, contact});
            saveAmbulanceData();
            clearFields();
        }
    }

    private void removeAmbulance() {
        int row = ambulanceTable.getSelectedRow();
        if (row != -1) {
            model.removeRow(row);
            saveAmbulanceData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an ambulance to remove.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }



    private void clearFields() {
        ambulanceIDField.setText("");
        driverNameField.setText("");
        contactField.setText("");
    }

    private void saveAmbulanceData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                writer.write(model.getValueAt(i, 0) + "," + model.getValueAt(i, 1) + "," + model.getValueAt(i, 2));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAmbulanceData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    model.addRow(new Object[]{data[0], data[1], data[2]});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AmbulanceWindow();
    }
}
