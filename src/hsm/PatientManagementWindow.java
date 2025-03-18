package hsm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PatientManagementWindow extends JFrame {
    public PatientManagementWindow() {
        setTitle("Patient Management");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        // Set background color
        getContentPane().setBackground(new Color(240, 240, 240));

        // Create Tabbed Panel
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 16)); // Change font for tab titles

        // Add tabs for each feature
        tabbedPane.addTab("Add Patient", new AddPatientPanel());
        tabbedPane.addTab("Update Patient", new UpdatePatientPanel());
        tabbedPane.addTab("Patient Info", new PatientInfoPanel());
        tabbedPane.addTab("Patient Discharge", new DischargePatientPanel());
        

        // Add tab panel to window
        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // ✅ Panel for "Add Patient"
    class AddPatientPanel extends JPanel {
        private JTextField nameField, ageField, addressField, phoneField, idField, diseaseField, symptomsField, depositField, doctorField;
        private JComboBox<String> genderBox;
        private JButton addButton;

        public AddPatientPanel() {
            setLayout(new GridBagLayout());
            setBackground(new Color(255, 255, 255)); // White background color

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Padding between components
            gbc.anchor = GridBagConstraints.WEST;

            // Label and Field Setup (example for Name)
            addLabelAndField(gbc, "Full Name:", nameField = new JTextField(20), 0);
            addLabelAndField(gbc, "Age:", ageField = new JTextField(20), 1);
            addLabelAndField(gbc, "Gender:", genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"}), 2);
            addLabelAndField(gbc, "Address:", addressField = new JTextField(20), 3);
            addLabelAndField(gbc, "Phone:", phoneField = new JTextField(20), 4);
            addLabelAndField(gbc, "ID:", idField = new JTextField(20), 5);
            addLabelAndField(gbc, "Disease:", diseaseField = new JTextField(20), 6);
            addLabelAndField(gbc, "Symptoms:", symptomsField = new JTextField(20), 7);
            addLabelAndField(gbc, "Deposit Amount:", depositField = new JTextField(20), 8);
            addLabelAndField(gbc, "Doctor Name:", doctorField = new JTextField(20), 9);

            // Add Button
            addButton = new JButton("Add Patient");
            addButton.setFont(new Font("Arial", Font.BOLD, 14));
            addButton.setBackground(new Color(30, 144, 255)); // Button background color
            addButton.setForeground(Color.WHITE); // Button text color
            addButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Border effect
            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.gridwidth = 2;
            add(addButton, gbc);

            // Action listener for the add button
            addButton.addActionListener(e -> addPatient());
        }

        private void addLabelAndField(GridBagConstraints gbc, String labelText, JComponent field, int row) {
            JLabel label = new JLabel(labelText);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.gridwidth = 1;
            add(label, gbc);

            gbc.gridx = 1;
            add(field, gbc);
        }

        private void addPatient() {
            String name = nameField.getText().trim();
            String age = ageField.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            String address = addressField.getText().trim();
            String phone = phoneField.getText().trim();
            String id = idField.getText().trim();
            String disease = diseaseField.getText().trim();
            String symptoms = symptomsField.getText().trim();
            String deposit = depositField.getText().trim();
            String doctor = doctorField.getText().trim();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // ✅ Input Validation
            if (name.isEmpty() || age.isEmpty() || address.isEmpty() || phone.isEmpty() || id.isEmpty() ||
                    disease.isEmpty() || symptoms.isEmpty() || deposit.isEmpty() || doctor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!age.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Age must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Phone number must be 10 digits!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!deposit.matches("\\d+(\\.\\d{1,2})?")) {
                JOptionPane.showMessageDialog(this, "Deposit must be a valid amount!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ✅ Save to file using the new `PatientDataHandler` class
            PatientDataHandler.savePatient(id, name, age, gender, address, phone, disease, symptoms, dateTime, deposit, doctor);

            clearFields();
        }

        private void clearFields() {
            nameField.setText("");
            ageField.setText("");
            addressField.setText("");
            phoneField.setText("");
            idField.setText("");
            diseaseField.setText("");
            symptomsField.setText("");
            depositField.setText("");
            doctorField.setText("");
        }
    }

    // ✅ Panel for "Update Patient"
    class UpdatePatientPanel extends JPanel {
        private JTextField idField, addressField, phoneField;
        private JButton searchButton, updateButton;

        public UpdatePatientPanel() {
            setLayout(new GridBagLayout());
            setBackground(new Color(255, 255, 255));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Padding between components

            // Label and Field Setup
            JLabel label = new JLabel("Search Patient ID:");
            label.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            add(label, gbc);

            idField = new JTextField(20);
            gbc.gridx = 1;
            add(idField, gbc);

            searchButton = new JButton("Search");
            searchButton.setFont(new Font("Arial", Font.BOLD, 14));
            searchButton.setBackground(new Color(30, 144, 255)); // Button background color
            searchButton.setForeground(Color.WHITE); // Button text color
            gbc.gridx = 2;
            add(searchButton, gbc);

            label = new JLabel("New Address:");
            label.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(label, gbc);

            addressField = new JTextField(20);
            gbc.gridx = 1;
            add(addressField, gbc);

            label = new JLabel("New Phone:");
            label.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(label, gbc);

            phoneField = new JTextField(20);
            gbc.gridx = 1;
            add(phoneField, gbc);

            updateButton = new JButton("Update Patient");
            updateButton.setFont(new Font("Arial", Font.BOLD, 14));
            updateButton.setBackground(new Color(30, 144, 255)); // Button background color
            updateButton.setForeground(Color.WHITE); // Button text color
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            add(updateButton, gbc);

            searchButton.addActionListener(e -> searchPatient());
            updateButton.addActionListener(e -> updatePatient());
        }

        private void searchPatient() {
            String id = idField.getText().trim();
            List<String[]> patients = PatientDataHandler.getAllPatients();
            boolean found = false;

            for (String[] patient : patients) {
                if (patient[0].equals(id)) {
                    addressField.setText(patient[4]);
                    phoneField.setText(patient[5]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Patient ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void updatePatient() {
            String id = idField.getText().trim();
            String newAddress = addressField.getText().trim();
            String newPhone = phoneField.getText().trim();

            if (id.isEmpty() || newAddress.isEmpty() || newPhone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPhone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Phone number must be 10 digits!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PatientDataHandler.updatePatient(id, newAddress, newPhone);
        }
    }

    // ✅ Panel for "Patient Info"
    class PatientInfoPanel extends JPanel {
        private JTable table;
        private DefaultTableModel tableModel;
        private JButton refreshButton;

        public PatientInfoPanel() {
            setLayout(new BorderLayout());
            setBackground(new Color(255, 255, 255));

            String[] columns = {"ID", "Name", "Age", "Gender", "Address", "Phone", "Disease", "Symptoms", "Date/Time", "Deposit", "Doctor"};
            tableModel = new DefaultTableModel(columns, 0);
            table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);

            refreshButton = new JButton("Refresh");
            refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
            refreshButton.setBackground(new Color(30, 144, 255)); // Button background color
            refreshButton.setForeground(Color.WHITE); // Button text color
            add(refreshButton, BorderLayout.SOUTH);

            refreshButton.addActionListener(e -> loadPatientData());
            loadPatientData();
        }

        private void loadPatientData() {
            List<String[]> patients = PatientDataHandler.getAllPatients();
            tableModel.setRowCount(0);
            for (String[] patient : patients) {
                tableModel.addRow(patient);
            }
        }
    }

    // ✅ Panel for "Discharge Patient"
    class DischargePatientPanel extends JPanel {
        private JTextField idField;

        public DischargePatientPanel() {
            setLayout(new GridBagLayout());
            setBackground(new Color(255, 255, 255));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            JLabel label = new JLabel("Enter Patient ID to Discharge:");
            label.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(label, gbc);

            idField = new JTextField(20);
            gbc.gridx = 1;
            add(idField, gbc);

            JButton dischargeButton = new JButton("Discharge Patient");
            dischargeButton.setFont(new Font("Arial", Font.BOLD, 14));
            dischargeButton.setBackground(new Color(30, 144, 255)); // Button background color
            dischargeButton.setForeground(Color.WHITE); // Button text color
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            add(dischargeButton, gbc);

            dischargeButton.addActionListener(e -> dischargePatient());
        }

        private void dischargePatient() {
            String id = idField.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Patient ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = PatientDataHandler.removePatient(id);
            if (success) {
                JOptionPane.showMessageDialog(this, "Patient discharged successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                idField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Patient ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
       public static void main(String[] args) {
        new PatientManagementWindow();
    }
}




 