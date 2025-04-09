package hsm;

import javax.swing.*;
import java.awt.*;

public class AddAdminWindow extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton addButton, removeButton, backButton;

    public AddAdminWindow() {
        setTitle("Manage Admins");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Back button at the top
        backButton = new JButton("Back");
        
        styleButton(backButton, new Color(244, 67, 54));
        backButton.setFocusPainted(false);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        // Center panel for form and action buttons
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        centerPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        centerPanel.add(usernameField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        centerPanel.add(passwordField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        addButton = new JButton("Add Admin");
        removeButton = new JButton("Remove Admin");

        addButton.setFocusPainted(false);
        removeButton.setFocusPainted(false);

        styleButton(addButton, new Color(76, 175, 80));
        styleButton(removeButton, new Color(255, 152, 0));

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.setBackground(centerPanel.getBackground());

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        centerPanel.add(buttonPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);

        // Action Listeners
        addButton.addActionListener(e -> addAdmin());
        removeButton.addActionListener(e -> removeAdmin());
        backButton.addActionListener(e -> {
            setVisible(false);
            new Reception(); // Optional: replace with your navigation
        });
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setPreferredSize(new Dimension(140, 35));
        button.setBorder(BorderFactory.createEmptyBorder());
    }

    private void addAdmin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (Data.addUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Admin added successfully!");
            usernameField.setText("");
            passwordField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error adding admin. Please check input or file access.");
        }
    }

    private void removeAdmin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (Data.removeUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Admin removed successfully!");
            usernameField.setText("");
            passwordField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Admin not found or error occurred.");
        }
    }

    public static void main(String[] args) {
        new AddAdminWindow();
    }
}
