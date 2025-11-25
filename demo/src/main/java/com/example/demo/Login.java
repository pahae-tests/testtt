package com.example.demo;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {

    private JTextField username;
    private JPasswordField password;

    public Login() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblUser = new JLabel("Username :");
        JLabel lblPass = new JLabel("Password :");

        username = new JTextField(15);
        password = new JPasswordField(15);

        JButton btnLogin = new JButton("Login");

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        add(lblUser, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        add(username, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(lblPass, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        add(password, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        add(btnLogin, gbc);
    }
}