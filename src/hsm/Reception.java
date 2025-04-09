package hsm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Reception extends JFrame {

    // Declare the buttons
    JButton patientManagement, allEmployees, room, ambulance, department, addAdmin, bloodBank, logout;

    public Reception() {
        setTitle("Hospital Reception");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout()); // Use BorderLayout for better scaling

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 15, 15)); // 3 rows, 3 columns with spacing
        buttonPanel.setBackground(new Color(210, 245, 225)); // Set a light background color

        // Creating Buttons with original colors
        patientManagement = createStyledButton("Patient Management", new Color(40, 167, 69)); // Green
        allEmployees = createStyledButton("All Employee Info", new Color(0, 123, 255)); // Blue
        ambulance = createStyledButton("Hospital Ambulance", new Color(220, 53, 69)); // Red
        addAdmin = createStyledButton("Add New Admin", new Color(32, 201, 151)); // Cyan
        bloodBank = createStyledButton("Blood Bank", new Color(214, 51, 132)); // Purple
        logout = createStyledButton("Logout", new Color(139, 0, 0)); // Dark Red

        // Add buttons to the panel
        buttonPanel.add(patientManagement);
        buttonPanel.add(allEmployees);
        buttonPanel.add(ambulance);
        buttonPanel.add(addAdmin);
        buttonPanel.add(bloodBank);
        buttonPanel.add(logout);

        // Add the panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Action Listeners for buttons
        patientManagement.addActionListener((ActionEvent e) -> {
            setVisible(false);
            new PatientManagementWindow(); // Navigate to patient management
        });

        allEmployees.addActionListener((ActionEvent e) -> {
            setVisible(false);
            new EmployeeInfoWindow(); // Navigate to employee info
        });

        ambulance.addActionListener((ActionEvent e) -> {
            setVisible(false);
            new AmbulanceWindow(); // Navigate to ambulance window
        });

        addAdmin.addActionListener((ActionEvent e) -> {
            setVisible(false);
            new AddAdminWindow(); // Navigate to add new admin window
        });

        bloodBank.addActionListener((ActionEvent e) -> {
            setVisible(false); // Hide Reception
            new BloodBankWindow(); // Navigate to blood bank window
        });

        logout.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                setVisible(false);
                new Login(); // Navigate to login screen
            }
        });

        setVisible(true);
    }

    // Method to create a styled button
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Simple font style
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false); // Remove focus paint when clicked
        button.setBorder(BorderFactory.createLineBorder(bgColor.darker(), 2)); // Simple border

        return button;
    }

    public static void main(String[] args) {
        new Reception(); // Start the reception window
    }
}
