package hsm;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDataHandler {
    private static final String FILE_NAME = "patients.txt";

    // ✅ Method to save patient data to a file
    public static void savePatient(String id, String name, String age, String gender, String address,
                                   String phone, String disease, String symptoms, String dateTime,
                                   String deposit, String doctor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(id + "," + name + "," + age + "," + gender + "," + address + "," + phone + ","
                    + disease + "," + symptoms + "," + dateTime + "," + deposit + "," + doctor);
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Patient added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving patient record!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ✅ Method to get all patients from the file
    public static List<String[]> getAllPatients() {
        List<String[]> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                patients.add(line.split(","));
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading patient records!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return patients;
    }

    // ✅ Method to discharge a patient (Remove by ID)
    public static boolean removePatient(String id) {
        List<String[]> patients = getAllPatients();
        boolean removed = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String[] patient : patients) {
                if (!patient[0].equals(id)) {  // Keep only records that don't match the ID
                    writer.write(String.join(",", patient));
                    writer.newLine();
                } else {
                    removed = true;
                }
            }
            if (removed) {
                JOptionPane.showMessageDialog(null, "Patient discharged successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Patient ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error discharging patient!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return removed;
    }

    // ✅ Method to update patient details
    public static void updatePatient(String id, String newAddress, String newPhone) {
        List<String[]> patients = getAllPatients();
        boolean updated = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String[] patient : patients) {
                if (patient[0].equals(id)) {
                    // Update the patient's details (address and phone)
                    patient[4] = newAddress;
                    patient[5] = newPhone;
                    updated = true;
                }
                // Write the updated or original patient data to file
                writer.write(String.join(",", patient));
                writer.newLine();
            }

            if (updated) {
                JOptionPane.showMessageDialog(null, "Patient updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Patient ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error updating patient record!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
}
