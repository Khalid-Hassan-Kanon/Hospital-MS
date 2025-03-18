package hsm;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Reception extends JFrame {

    JButton patientManagement, allEmployees, room, ambulance, department, searchRoom, logout, addAdmin, bloodBank;

    public Reception() {
        setTitle("Hospital Reception");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout()); // Using BorderLayout for better scaling

        // Button Font
        Font buttonFont = new Font("Tahoma", Font.BOLD, 14);

        // Panel to hold buttons in a grid layout (3 columns)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 15, 15)); // 3 rows, 3 columns, with spacing
        buttonPanel.setBackground(new Color(210, 245, 225)); // Background color

        // Creating Buttons
        patientManagement = createStyledButton("Patient Management", buttonFont, new Color(40, 167, 69));
        allEmployees = createStyledButton("All Employee Info", buttonFont, new Color(0, 123, 255));
        room = createStyledButton("Room", buttonFont, new Color(23, 162, 184));
        ambulance = createStyledButton("Hospital Ambulance", buttonFont, new Color(220, 53, 69));
        department = createStyledButton("Department", buttonFont, new Color(232, 62, 140));
        searchRoom = createStyledButton("Search Room", buttonFont, new Color(10, 150, 228));
        addAdmin = createStyledButton("Add New Admin", buttonFont, new Color(32, 201, 151));
        bloodBank = createStyledButton("Blood Bank", buttonFont, new Color(214, 51, 132));
        logout = createStyledButton("Logout", buttonFont, new Color(139, 0, 0));

        // Adding buttons to the panel
        buttonPanel.add(patientManagement);
        buttonPanel.add(allEmployees);
        buttonPanel.add(room);
        buttonPanel.add(ambulance);
        buttonPanel.add(department);
        buttonPanel.add(searchRoom);
        buttonPanel.add(addAdmin);
        buttonPanel.add(bloodBank);
        buttonPanel.add(logout);

        // Adding panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Action Listeners
          patientManagement.addActionListener((ActionEvent e) -> new PatientManagementWindow());
//        allEmployees.addActionListener((ActionEvent e) -> new EmployeeInfoWindow());
//        room.addActionListener((ActionEvent e) -> new RoomWindow());
//        ambulance.addActionListener((ActionEvent e) -> new AmbulanceWindow());
//        department.addActionListener((ActionEvent e) -> new DepartmentWindow());
//        searchRoom.addActionListener((ActionEvent e) -> new SearchRoomWindow());
//        addAdmin.addActionListener((ActionEvent e) -> new AddAdminWindow());
//        bloodBank.addActionListener((ActionEvent e) -> new BloodBankWindow());

        logout.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                setVisible(false);
                new Login();
            }
        });

        setVisible(true);
    }

    private JButton createStyledButton(String text, Font font, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(bgColor.darker(), 2, true));

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
