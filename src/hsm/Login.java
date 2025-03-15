package hsm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField jPasswordField;
    JButton jsignupButton, jclearButton, exitButton;

    public Login() {
        setTitle("Hospital Management System - Login");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(210, 245, 225));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Title Label
        JLabel titleLabel = new JLabel("Hospital Management System");
        titleLabel.setBounds(200, 30, 400, 40);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 80, 160));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        // Centering X Position
        int centerX = (800 - 300) / 2;

        // Username Label
        JLabel nameLabel = new JLabel("Username:");
        nameLabel.setBounds(centerX, 150, 100, 30);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        nameLabel.setForeground(Color.BLACK);
        add(nameLabel);

        // Username Field with Placeholder
        textField = new JTextField("Enter Username");
        textField.setBounds(centerX + 120, 150, 200, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setForeground(Color.GRAY);
        textField.setBackground(new Color(230, 230, 230));
        addPlaceholderEffect(textField, "Enter Username");
        add(textField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(centerX, 200, 100, 30);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        passwordLabel.setForeground(Color.BLACK);
        add(passwordLabel);

        // Password Field with Placeholder
        jPasswordField = new JPasswordField("Enter Password");
        jPasswordField.setBounds(centerX + 120, 200, 200, 30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jPasswordField.setForeground(Color.GRAY);
        jPasswordField.setBackground(new Color(230, 230, 230));
        addPasswordPlaceholder(jPasswordField, "Enter Password");
        add(jPasswordField);

        // Login Button
        jsignupButton = new JButton("Login");
        jsignupButton.setBounds(centerX + 40, 260, 120, 40);
        jsignupButton.setBackground(new Color(0, 122, 204));
        jsignupButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        jsignupButton.setForeground(Color.WHITE);
        jsignupButton.setFocusPainted(false);
        jsignupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jsignupButton.addActionListener(this);
        add(jsignupButton);

        // Clear Button
        jclearButton = new JButton("Clear");
        jclearButton.setBounds(centerX + 180, 260, 120, 40);
        jclearButton.setBackground(new Color(204, 0, 0));
        jclearButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        jclearButton.setForeground(Color.WHITE);
        jclearButton.setFocusPainted(false);
        jclearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jclearButton.addActionListener(this);
        add(jclearButton);

        // Exit Button
        exitButton = new JButton("Exit");
        exitButton.setBounds(centerX + 100, 330, 120, 40);
        exitButton.setBackground(new Color(128, 0, 0)); // Dark red color
        exitButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        add(exitButton);

        setVisible(true);
    }

    // Method to add placeholder effect to JTextField
    private void addPlaceholderEffect(JTextField field, String placeholder) {
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    // Method to add placeholder effect to JPasswordField
    private void addPasswordPlaceholder(JPasswordField field, String placeholder) {
        field.setEchoChar((char) 0); // Make text visible for placeholder
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('•'); // Mask password input
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getPassword().length == 0) {
                    field.setEchoChar((char) 0); // Show text for placeholder
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jsignupButton) {
            try {
                String username = textField.getText();
                String password = new String(jPasswordField.getPassword());

                // Validate login using Data class
                if (Data.validateCredentials(username, password)) {
                    //JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new Reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == jclearButton) {
            // Clear username and password fields and reset placeholders
            textField.setText("Enter Username");
            textField.setForeground(Color.GRAY);
            jPasswordField.setText("Enter Password");
            jPasswordField.setForeground(Color.GRAY);
            jPasswordField.setEchoChar((char) 0); // Placeholder visible for password field
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
