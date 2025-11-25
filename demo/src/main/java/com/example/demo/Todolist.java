package com.example.demo;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Todolist extends JPanel {

    private DefaultListModel<String> todoListModel;
    private JList<String> todoList;
    private JTextField inputField;

    public Todolist() {
        setLayout(new BorderLayout());

        todoListModel = new DefaultListModel<>();
        todoList = new JList<>(todoListModel);
        inputField = new JTextField();

        JButton addButton = new JButton("Ajouter");
        JButton refreshButton = new JButton("Actualiser");
        JButton deleteButton = new JButton("Supprimer");

        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(addButton);
        panel.add(refreshButton);
        panel.add(deleteButton);

        add(new JScrollPane(todoList), BorderLayout.CENTER);
        add(inputField, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> loadTodos());
        addButton.addActionListener(e -> addTodo());
        deleteButton.addActionListener(e -> deleteTodo());

        loadTodos();
    }

    private void loadTodos() {
        todoListModel.clear();
        try {
            URL url = new URL("http://localhost:8080/api/todos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null)
                response.append(line);
            reader.close();

            String[] parts = response.toString().split("\\},\\{");
            for (String part : parts) {
                String title = part.contains("title") ? part.split("\"title\":\"")[1].split("\"")[0] : "??";
                todoListModel.addElement(title);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTodo() {
        String title = inputField.getText().trim();
        if (title.isEmpty()) return;

        try {
            URL url = new URL("http://localhost:8080/api/todos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            String json = "{\"title\":\"" + title + "\",\"completed\":false}";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }
            conn.getResponseCode();
            inputField.setText("");
            loadTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteTodo() {
        int index = todoList.getSelectedIndex();
        if (index == -1) return;

        String title = todoListModel.get(index);

        try {
            URL url = new URL("http://localhost:8080/api/todos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String response = reader.readLine();
            reader.close();

            String[] entries = response.split("\\},\\{");
            for (String entry : entries) {
                if (entry.contains("\"title\":\"" + title + "\"")) {
                    String id = entry.split("\"id\":")[1].split(",")[0];
                    URL delUrl = new URL("http://localhost:8080/api/todos/" + id);
                    HttpURLConnection delConn = (HttpURLConnection) delUrl.openConnection();
                    delConn.setRequestMethod("DELETE");
                    delConn.getResponseCode();
                    break;
                }
            }
            loadTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}