//
//package hsm;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EmployeeInfoWindow extends JFrame {
//    private JTable doctorTable;
//    private DefaultTableModel tableModel;
//    private JButton addDoctorButton, showAllDoctorsButton, searchDoctorButton, removeDoctorButton, backButton;
//    private static final String FILE_NAME = "doctors.txt";
//
//    public EmployeeInfoWindow() {
//        setTitle("Employee Management - Doctors");
//        setSize(700, 400);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Table Setup
//        String[] columns = {"Doctor ID", "Name", "Specialization"};
//        tableModel = new DefaultTableModel(columns, 0);
//        doctorTable = new JTable(tableModel);
//        doctorTable.setRowHeight(30);
//        doctorTable.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        JScrollPane scrollPane = new JScrollPane(doctorTable);
//        add(scrollPane, BorderLayout.CENTER);
//
//        // Buttons Panel
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10));
//
//        addDoctorButton = new JButton("Add Doctor");
//        showAllDoctorsButton = new JButton("Show All Doctors");
//        searchDoctorButton = new JButton("Search Doctor by ID");
//        removeDoctorButton = new JButton("Remove Doctor");
//        backButton = new JButton("Back");
//        backButton.addActionListener((ActionEvent e)->
//        {
//            setVisible(false);
//            new Reception();
//        });
//
//        buttonPanel.add(addDoctorButton);
//        buttonPanel.add(showAllDoctorsButton);
//        buttonPanel.add(searchDoctorButton);
//        buttonPanel.add(removeDoctorButton);
//        buttonPanel.add(backButton);
//
//        add(buttonPanel, BorderLayout.SOUTH);
//
//        // Button Listeners
//        addDoctorButton.addActionListener((ActionEvent e) -> addDoctor());
//        showAllDoctorsButton.addActionListener((ActionEvent e) -> loadDoctorsFromFile());
//        searchDoctorButton.addActionListener((ActionEvent e) -> searchDoctorById());
//        removeDoctorButton.addActionListener((ActionEvent e) -> removeDoctor());
//        backButton.addActionListener((ActionEvent e) -> dispose());
//
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    // Method to Add Doctor
//    private void addDoctor() {
//        String id = JOptionPane.showInputDialog("Enter Doctor ID:");
//        String name = JOptionPane.showInputDialog("Enter Doctor Name:");
//        String specialization = JOptionPane.showInputDialog("Enter Specialization:");
//
//        if (id != null && name != null && specialization != null && !id.isEmpty() && !name.isEmpty() && !specialization.isEmpty()) {
//            String doctorRecord = id + "," + name + "," + specialization;
//            saveDoctorToFile(doctorRecord);
//            tableModel.addRow(new String[]{id, name, specialization});
//            JOptionPane.showMessageDialog(this, "Doctor Added Successfully!");
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid Input! All fields are required.");
//        }
//    }
//
//    // Method to Save Doctor Data to File
//    private void saveDoctorToFile(String record) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
//            writer.write(record);
//            writer.newLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to Load Doctors from File
//    private void loadDoctorsFromFile() {
//        tableModel.setRowCount(0);
//        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] doctorData = line.split(",");
//                tableModel.addRow(doctorData);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to Search Doctor by ID
//    private void searchDoctorById() {
//        String searchId = JOptionPane.showInputDialog("Enter Doctor ID to Search:");
//        if (searchId == null || searchId.isEmpty()) {
//            return;
//        }
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
//            String line;
//            boolean found = false;
//            while ((line = reader.readLine()) != null) {
//                String[] doctorData = line.split(",");
//                if (doctorData[0].equalsIgnoreCase(searchId)) {
//                    JOptionPane.showMessageDialog(this, "Doctor Found:\nID: " + doctorData[0] + "\nName: " + doctorData[1] + "\nSpecialization: " + doctorData[2]);
//                    found = true;
//                    break;
//                }
//            }
//            if (!found) {
//                JOptionPane.showMessageDialog(this, "Doctor with ID " + searchId + " not found.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to Remove Doctor
//    private void removeDoctor() {
//        String removeId = JOptionPane.showInputDialog("Enter Doctor ID to Remove:");
//        if (removeId == null || removeId.isEmpty()) {
//            return;
//        }
//
//        List<String> doctorsList = new ArrayList<>();
//        boolean found = false;
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] doctorData = line.split(",");
//                if (!doctorData[0].equalsIgnoreCase(removeId)) {
//                    doctorsList.add(line);
//                } else {
//                    found = true;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (found) {
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
//                for (String record : doctorsList) {
//                    writer.write(record);
//                    writer.newLine();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            loadDoctorsFromFile(); // Refresh the table
//            JOptionPane.showMessageDialog(this, "Doctor Removed Successfully!");
//        } else {
//            JOptionPane.showMessageDialog(this, "Doctor with ID " + removeId + " not found.");
//        }
//    }
//
//    public static void main(String[] args) {
//        new EmployeeInfoWindow();
//    }
//}
package hsm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeInfoWindow extends JFrame {

    private JTable doctorTable;
    private DefaultTableModel tableModel;
    private JButton addDoctorButton, showAllDoctorsButton, searchDoctorButton, removeDoctorButton, backButton;
    private static final String FILE_NAME = "doctors.txt";

    public EmployeeInfoWindow() {
        setTitle("Employee Management - Doctors");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table Setup
        String[] columns = {"Doctor ID", "Full Name", "Age", "Gender", "Address", "Phone", "Specialization", "Experience", "Fee"};
        tableModel = new DefaultTableModel(columns, 0);
        doctorTable = new JTable(tableModel);
        doctorTable.setRowHeight(30);
        doctorTable.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(doctorTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10));

        addDoctorButton = new JButton("Add Doctor");
        showAllDoctorsButton = new JButton("Show All Doctors");
        searchDoctorButton = new JButton("Search Doctor by ID");
        removeDoctorButton = new JButton("Remove Doctor");
        backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent e)
                -> {
            setVisible(false);
            new Reception();
        });

        buttonPanel.add(addDoctorButton);
        buttonPanel.add(showAllDoctorsButton);
        buttonPanel.add(searchDoctorButton);
        buttonPanel.add(removeDoctorButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button Listeners
        addDoctorButton.addActionListener((ActionEvent e) -> addDoctor());
        showAllDoctorsButton.addActionListener((ActionEvent e) -> loadDoctorsFromFile());
        searchDoctorButton.addActionListener((ActionEvent e) -> searchDoctorById());
        removeDoctorButton.addActionListener((ActionEvent e) -> removeDoctor());
        backButton.addActionListener((ActionEvent e) -> dispose());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to Add Doctor with Form
    private void addDoctor() {
        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(20);
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JTextField addressField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField idField = new JTextField(20);
        JTextField specializationField = new JTextField(20);
        JTextField experienceField = new JTextField(20);
        JTextField feeField = new JTextField(20);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        addLabelAndField(panel, gbc, "Full Name:", nameField);
        addLabelAndField(panel, gbc, "Age:", ageField);
        addLabelAndField(panel, gbc, "Gender:", genderBox);
        addLabelAndField(panel, gbc, "Address:", addressField);
        addLabelAndField(panel, gbc, "Phone:", phoneField);
        addLabelAndField(panel, gbc, "ID:", idField);
        addLabelAndField(panel, gbc, "Specialization:", specializationField);
        addLabelAndField(panel, gbc, "Experience (Years):", experienceField);
        addLabelAndField(panel, gbc, "Consultation Fee:", feeField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Enter Doctor Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String age = ageField.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            String address = addressField.getText().trim();
            String phone = phoneField.getText().trim();
            String specialization = specializationField.getText().trim();
            String experience = experienceField.getText().trim();
            String fee = feeField.getText().trim();

            if (!id.isEmpty() && !name.isEmpty() && !age.isEmpty() && !address.isEmpty() && !phone.isEmpty() && !specialization.isEmpty() && !experience.isEmpty() && !fee.isEmpty()) {
                String doctorRecord = id + "," + name + "," + age + "," + gender + "," + address + "," + phone + "," + specialization + "," + experience + "," + fee;
                saveDoctorToFile(doctorRecord);
                tableModel.addRow(new String[]{id, name, age, gender, address, phone, specialization, experience, fee});
                JOptionPane.showMessageDialog(this, "Doctor Added Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Input! All fields are required.");
            }
        }
    }

    // Utility Method to Add Label and Field in GridBagLayout
    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
        gbc.gridy++;
    }

    // Method to Save Doctor Data to File
    private void saveDoctorToFile(String record) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to Load Doctors from File
    private void loadDoctorsFromFile() {
        tableModel.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] doctorData = line.split(",");
                tableModel.addRow(doctorData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to Search Doctor by ID
    private void searchDoctorById() {
        String searchId = JOptionPane.showInputDialog("Enter Doctor ID to Search:");
        if (searchId == null || searchId.isEmpty()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] doctorData = line.split(",");
                if (doctorData[0].equalsIgnoreCase(searchId)) {
                    JOptionPane.showMessageDialog(this, "Doctor Found:\nID: " + doctorData[0] + "\nName: " + doctorData[1] + "\nAge: " + doctorData[2] + "\nGender: " + doctorData[3] + "\nAddress: " + doctorData[4] + "\nPhone: " + doctorData[5] + "\nSpecialization: " + doctorData[6] + "\nExperience: " + doctorData[7] + " years\nFee: $" + doctorData[8]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Doctor with ID " + searchId + " not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to Remove Doctor
    private void removeDoctor() {
        String removeId = JOptionPane.showInputDialog("Enter Doctor ID to Remove:");
        if (removeId == null || removeId.isEmpty()) {
            return;
        }

        List<String> doctorsList = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(removeId + ",")) {
                    doctorsList.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                for (String record : doctorsList) {
                    writer.write(record);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            loadDoctorsFromFile();
            JOptionPane.showMessageDialog(this, "Doctor Removed Successfully!");
        }
    }

    public static void main(String[] args) {
        new EmployeeInfoWindow();
    }
}
