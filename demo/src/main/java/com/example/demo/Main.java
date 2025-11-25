package com.example.demo;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main extends JFrame {

    private JPanel container;
    private CardLayout cardLayout;

    public Main() {
        setTitle("Application");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Map<String, JPanel> pages = new LinkedHashMap<>();
        pages.put("Login", new Login());
        pages.put("Todo List", new Todolist());
        pages.put("Home", new Home());
        Set<String> headerElements = Set.of("Todo List", "Home");

        // --- HEADER ---
        JPanel header = new JPanel(new FlowLayout());

        // --- CardLayout ---
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // Génération dynamique
        pages.forEach((name, panel) -> {
            container.add(panel, name);
            if (headerElements.contains(name)) {
                JButton btn = new JButton(name);
                btn.addActionListener(e -> cardLayout.show(container, name));
                header.add(btn);
            }
        });

        add(header, BorderLayout.NORTH);
        add(container, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}