package client;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    public LoginGUI() {

        setTitle("Login");

        setSize(300, 200);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JTextField usernameField =
                new JTextField(15);

        JPasswordField passwordField =
                new JPasswordField(15);

        JButton loginButton =
                new JButton("Login");

        panel.add(new JLabel("Username"));
        panel.add(usernameField);

        panel.add(new JLabel("Password"));
        panel.add(passwordField);

        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e -> {

            String username = usernameField.getText();

            String password =
                    new String(passwordField.getPassword());

            loginButton.setEnabled(false);

            new Thread(() -> {

                new ChatClient(
                        "localhost",
                        5000,
                        username,
                        password
                );

                SwingUtilities.invokeLater(() -> {
                    loginButton.setEnabled(true);
                });

            }).start();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}