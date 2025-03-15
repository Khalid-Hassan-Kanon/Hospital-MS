package hsm;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Reception extends JFrame {

    JButton addPatient, allEmployees, updatePatient, room, patientInfo,
            ambulance, department, discharge, searchRoom, logout, addAdmin, bloodBank;

    public Reception() {
        setTitle("Hospital Reception");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 3, 20, 20)); // 4 rows, 3 columns, with spacing
        getContentPane().setBackground(new Color(210, 245, 225));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Button Font
        Font buttonFont = new Font("Tahoma", Font.BOLD, 14);

        // Creating Buttons with Colors
        addPatient = createStyledButton("Add New Patient", buttonFont, new Color(40, 167, 69));
        allEmployees = createStyledButton("All Employee Info", buttonFont, new Color(0, 123, 255));
        updatePatient = createStyledButton("Update Patient Details", buttonFont, new Color(253, 126, 20));
        room = createStyledButton("Room", buttonFont, new Color(23, 162, 184));
        patientInfo = createStyledButton("Patient Info", buttonFont, new Color(111, 66, 193));
        ambulance = createStyledButton("Hospital Ambulance", buttonFont, new Color(220, 53, 69));
        department = createStyledButton("Department", buttonFont, new Color(232, 62, 140));
        discharge = createStyledButton("Patient Discharge", buttonFont, new Color(52, 58, 64));
        searchRoom = createStyledButton("Search Room", buttonFont, new Color(10, 150, 228)); // Turquoise
        addAdmin = createStyledButton("Add New Admin", buttonFont, new Color(32, 201, 151));
        bloodBank = createStyledButton("Blood Bank", buttonFont, new Color(214, 51, 132));
        logout = createStyledButton("Logout", buttonFont, new Color(139, 0, 0));

        // Adding Buttons to the Frame
        add(addPatient);
        add(allEmployees);
        add(updatePatient);
        add(room);
        add(patientInfo);
        add(ambulance);
        add(department);
        add(discharge);
        add(searchRoom);
        add(addAdmin);
        add(bloodBank);
        add(logout);

        // Adding Action Listeners using Lambda Format
        addPatient.addActionListener((ActionEvent e) -> {
        });

        allEmployees.addActionListener((ActionEvent e) -> {
        });

        updatePatient.addActionListener((ActionEvent e) -> {
        });

        room.addActionListener((ActionEvent e) -> {
        });

        patientInfo.addActionListener((ActionEvent e) -> {
        });

        ambulance.addActionListener((ActionEvent e) -> {
        });

        department.addActionListener((ActionEvent e) -> {
        });

        discharge.addActionListener((ActionEvent e) -> {
        });

        searchRoom.addActionListener((ActionEvent e) -> {
        });

        addAdmin.addActionListener((ActionEvent e) -> {
        });

        bloodBank.addActionListener((ActionEvent e) -> {
        });

        logout.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to logout?", "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                setVisible(false); // Close this window
                new Login(); // Redirect to Login Page
            }
        });

        setVisible(true);
    }

    // Method to create styled buttons with specific colors
    private JButton createStyledButton(String text, Font font, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(bgColor.darker(), 2, true)); // Rounded border

        // Hover effect - Change button color slightly when mouse is over
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    public static void main(String[] args) {
        new Reception();
    }
}
