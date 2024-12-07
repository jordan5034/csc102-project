package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import games.AICompanion.src.AICompanionGameGUI;
import games.CookieDestroy.src.Main;

public class MainMenuGUI extends JFrame {

    public MainMenuGUI() {
        // Set up JFrame properties
        setTitle("Arcade Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Main panel for the content (customizable)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(44, 62, 80)); // Default background color

        // Title Label (Customizable)
        JLabel titleLabel = new JLabel("Arcade Main Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20));
        buttonPanel.setOpaque(false); // Transparent background to blend with main panel

        // Games

        String Game1 = "AICompanion";
        String Game2 = "Cookie Destroy";
        String Game3 = "Game 3";
        String Game4 = "Game 4";

        // Add buttons with dynamic behavior
        buttonPanel.add(createGameButton(Game1, e -> startGame1()));
        buttonPanel.add(createGameButton(Game2, e -> startGame2()));
        buttonPanel.add(createGameButton(Game3, e -> startGame3()));
        buttonPanel.add(createGameButton(Game4, e -> startGame4()));

        // Add components to the main panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(buttonPanel, gbc);

        // Add main panel to the JFrame
        add(mainPanel);

        // Make JFrame visible
        setVisible(true);
    }

    // Utility method to create game buttons dynamically
    private JButton createGameButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setFocusPainted(false);
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.addActionListener(action); // Attach the dynamic action listener
        return button;
    }

    // Methods for launching each game

    private void startGame1() {


        // User decides size of grid

        int n = getInputTwoIntegers("Input number of rows (5-100):", 5, 100);
        int m = getInputTwoIntegers("Input number of columns (5-100):", 5, 100);

        if (n > 0 && m > 0) {
            games.AICompanion.src.AICompanionGameGUI aiCompanionGameGUI = new games.AICompanion.src.AICompanionGameGUI(n, m);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input. Game not started.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startGame2() {
        games.CookieDestroy.src.Main main = new games.CookieDestroy.src.Main();

        // JOptionPane.showMessageDialog(this, "Launching Game 2!", "Game Selected", JOptionPane.INFORMATION_MESSAGE);
    }

    private void startGame3() {
        JOptionPane.showMessageDialog(this, "Launching Game 3!", "Game Selected", JOptionPane.INFORMATION_MESSAGE);
    }

    private void startGame4() {
        JOptionPane.showMessageDialog(this, "Launching Game 4!", "Game Selected", JOptionPane.INFORMATION_MESSAGE);
    }

    // Helper Methods

    private int getInputTwoIntegers(String message, int min, int max) {
        final JDialog dialog = new JDialog(this, "Input Required", true);
        final int[] result = {-1}; // Array to store the input result

        JTextField textField = new JTextField(10);
        JLabel label = new JLabel(message);
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // Panel setup
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(textField);
        panel.add(okButton);
        panel.add(cancelButton);
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);

        // Action for OK button
        okButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(textField.getText());
                if (value >= min && value <= max) {
                    result[0] = value; // Set the valid input
                    dialog.setVisible(false);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please enter a number between " + min + " and " + max + ".", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid input. Please enter an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action for Cancel button and ESC key
        cancelButton.addActionListener(e -> {
            dialog.setVisible(false);
            dialog.dispose();
        });

        // Add ESC key listener
        dialog.getRootPane().registerKeyboardAction(e -> {
            dialog.setVisible(false);
            dialog.dispose();
        }, KeyStroke.getKeyStroke("ESCAPE"), JComponent.WHEN_IN_FOCUSED_WINDOW);

        // Show dialog and wait for input
        dialog.setVisible(true);

        // Return the input value or -1 if canceled
        return result[0];
    }



}


