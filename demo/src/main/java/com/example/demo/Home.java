package com.example.demo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Home extends JPanel {
    // --- Palette de couleurs modernes ---
    private static final Color PRIMARY_COLOR = new Color(40, 42, 100); // Fond sombre élégant
    private static final Color SECONDARY_COLOR = new Color(68, 71, 90); // Gris bleuâtre
    private static final Color ACCENT_COLOR = new Color(139, 233, 253); // Bleu électrique
    private static final Color TEXT_COLOR = new Color(255, 255, 255); // Blanc
    private static final Color TEXT_SECONDARY = new Color(180, 180, 180); // Gris clair

    // --- Polices modernes ---
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font PRODUCT_TITLE_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font PRODUCT_PRICE_FONT = new Font("Segoe UI", Font.BOLD, 18);

    // --- Données simulées ---
    private List<Product> products = new ArrayList<>();
    private List<String> categories = List.of("Tous", "Électronique", "Mode", "Maison", "Beauté");

    public Home() {
        setLayout(new BorderLayout());
        setBackground(PRIMARY_COLOR);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // --- Initialisation des produits ---
        initProducts();

        // --- HEADER ---
        add(createHeader(), BorderLayout.NORTH);

        // --- CONTENU PRINCIPAL ---
        JPanel mainContent = new JPanel(new BorderLayout(0, 20));
        mainContent.setOpaque(false);

        // --- Bannière promotionnelle ---
        mainContent.add(createBanner(), BorderLayout.NORTH);

        // --- Filtres et produits ---
        JPanel centerPanel = new JPanel(new BorderLayout(20, 0));
        centerPanel.setOpaque(false);
        centerPanel.add(createFilters(), BorderLayout.WEST);
        centerPanel.add(createProductGrid(), BorderLayout.CENTER);

        mainContent.add(centerPanel, BorderLayout.CENTER);
        add(mainContent, BorderLayout.CENTER);
    }

    // --- Création du header ---
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(0, 0, 20, 0));

        // --- Logo ---
        JLabel logo = new JLabel("ELEGANCE", SwingConstants.LEFT);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        logo.setForeground(ACCENT_COLOR);

        // --- Barre de recherche ---
        JTextField searchField = new JTextField(20);
        searchField.setFont(BUTTON_FONT);
        searchField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        searchField.setBackground(SECONDARY_COLOR);
        searchField.setForeground(TEXT_COLOR);
        searchField.setCaretColor(ACCENT_COLOR);
        searchField.setPreferredSize(new Dimension(300, 40));

        // --- Bouton de recherche ---
        JButton searchButton = new JButton("🔍");
        searchButton.setFont(BUTTON_FONT);
        searchButton.setBackground(ACCENT_COLOR);
        searchButton.setForeground(PRIMARY_COLOR);
        searchButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        searchButton.setFocusPainted(false);

        // --- Panneau de recherche ---
        JPanel searchPanel = new JPanel(new BorderLayout(5, 0));
        searchPanel.setOpaque(false);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // --- Panneau utilisateur ---
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        userPanel.setOpaque(false);
        JButton cartButton = new JButton("🛒 Panier");
        JButton profileButton = new JButton("👤 Mon Compte");
        cartButton.setFont(BUTTON_FONT);
        profileButton.setFont(BUTTON_FONT);
        cartButton.setBackground(SECONDARY_COLOR);
        profileButton.setBackground(SECONDARY_COLOR);
        cartButton.setForeground(TEXT_COLOR);
        profileButton.setForeground(TEXT_COLOR);
        cartButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        profileButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        userPanel.add(cartButton);
        userPanel.add(profileButton);

        // --- Assemblage ---
        header.add(logo, BorderLayout.WEST);
        header.add(searchPanel, BorderLayout.CENTER);
        header.add(userPanel, BorderLayout.EAST);

        return header;
    }

    // --- Création de la bannière ---
    private JPanel createBanner() {
        JPanel banner = new JPanel(new BorderLayout());
        banner.setBackground(new Color(50, 50, 70));
        banner.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel bannerTitle = new JLabel("Découvrez nos offres exclusives !", SwingConstants.CENTER);
        bannerTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        bannerTitle.setForeground(TEXT_COLOR);

        JButton shopNowButton = new JButton("Acheter maintenant");
        shopNowButton.setFont(BUTTON_FONT);
        shopNowButton.setBackground(ACCENT_COLOR);
        shopNowButton.setForeground(PRIMARY_COLOR);
        shopNowButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(shopNowButton);

        banner.add(bannerTitle, BorderLayout.CENTER);
        banner.add(buttonPanel, BorderLayout.SOUTH);

        return banner;
    }

    // --- Création des filtres ---
    private JPanel createFilters() {
        JPanel filters = new JPanel(new BorderLayout());
        filters.setOpaque(false);
        filters.setPreferredSize(new Dimension(200, 0));

        JLabel filtersTitle = new JLabel("Filtres", SwingConstants.CENTER);
        filtersTitle.setFont(SUBTITLE_FONT);
        filtersTitle.setForeground(TEXT_COLOR);

        JPanel categoriesPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        categoriesPanel.setOpaque(false);

        for (String category : categories) {
            JButton categoryButton = new JButton(category);
            categoryButton.setFont(BUTTON_FONT);
            categoryButton.setBackground(SECONDARY_COLOR);
            categoryButton.setForeground(TEXT_COLOR);
            categoryButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            categoryButton.setFocusPainted(false);
            categoriesPanel.add(categoryButton);
        }

        filters.add(filtersTitle, BorderLayout.NORTH);
        filters.add(categoriesPanel, BorderLayout.CENTER);

        return filters;
    }

    // --- Création de la grille de produits ---
    private JPanel createProductGrid() {
        JPanel productGrid = new JPanel(new GridLayout(0, 3, 20, 20));
        productGrid.setOpaque(false);

        for (Product product : products) {
            productGrid.add(createProductCard(product));
        }

        return productGrid;
    }

    // --- Création d'une carte de produit ---
    private JPanel createProductCard(Product product) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(SECONDARY_COLOR);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Image du produit ---
        JLabel productImage = new JLabel("📱", SwingConstants.CENTER);
        productImage.setFont(new Font("Arial", Font.PLAIN, 50));
        productImage.setPreferredSize(new Dimension(0, 150));
        productImage.setBackground(new Color(80, 80, 100));
        productImage.setOpaque(true);

        // --- Titre du produit ---
        JLabel productTitle = new JLabel(product.getName(), SwingConstants.CENTER);
        productTitle.setFont(PRODUCT_TITLE_FONT);
        productTitle.setForeground(TEXT_COLOR);

        // --- Prix du produit ---
        JLabel productPrice = new JLabel(String.format("%.2f €", product.getPrice()), SwingConstants.CENTER);
        productPrice.setFont(PRODUCT_PRICE_FONT);
        productPrice.setForeground(ACCENT_COLOR);

        // --- Bouton "Ajouter au panier" ---
        JButton addToCartButton = new JButton("Ajouter au panier");
        addToCartButton.setFont(BUTTON_FONT);
        addToCartButton.setBackground(ACCENT_COLOR);
        addToCartButton.setForeground(PRIMARY_COLOR);
        addToCartButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        // --- Assemblage ---
        card.add(productImage, BorderLayout.NORTH);
        card.add(productTitle, BorderLayout.CENTER);
        card.add(productPrice, BorderLayout.SOUTH);
        card.add(addToCartButton, BorderLayout.PAGE_END);

        return card;
    }

    // --- Initialisation des produits ---
    private void initProducts() {
        products.add(new Product("iPhone 15 Pro", 1299.99));
        products.add(new Product("MacBook Air M2", 1499.99));
        products.add(new Product("AirPods Pro 2", 299.99));
        products.add(new Product("Apple Watch Series 9", 499.99));
        products.add(new Product("iPad Pro 12.9\"", 1199.99));
        products.add(new Product("HomePod Mini", 99.99));
    }

    // --- Classe interne pour les produits ---
    private static class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }
}