package src.gui.matrixoperations;

import gui.OperationButton;
import gui.SetMatrixVector;

import javax.swing.*;
import java.awt.*;

public class Determinant extends JButton implements OperationButton {
    private JPanel rightPanel; // Panel to update
    public void configurations(){
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
    }
    public Determinant(String name, JPanel panel) {
        super(name);
        this.rightPanel = panel; // Store the reference to the right panel
    }
    public void openPanel() {
        rightPanel.removeAll();  // Clear previous components

        // Styling components
        JLabel label = new JLabel("Matrix dimension:");
        JTextField field = new JTextField("2", 5);  // Default value 2

        // Set Matrix button
        SetMatrixVector setButton = new SetMatrixVector("Set Matrix");
        setButton.addActionListener(k -> {
            try {
                int dimension1 = Integer.parseInt(field.getText()); // Get number from field1
                setButton.openOneWindow(dimension1, dimension1, "Matrix", "Determinant");
            } catch (NumberFormatException e) {
                // Handle error if the text fields contain non-numeric values
                System.out.println("Please enter valid numbers in the dimension fields.");
            }
        });
        setButton.setBackground(Color.DARK_GRAY);
        setButton.setForeground(Color.WHITE);
        setButton.setFocusPainted(false);

        // Panel for matrix dimension
        JPanel dimensionPanel = new JPanel();
        dimensionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        dimensionPanel.add(label);
        dimensionPanel.add(field);

        // Using GridBagLayout for main panel layout
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;    // Align components in the first column
        gbc.gridwidth = GridBagConstraints.REMAINDER;  // End row
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Allow horizontal stretching for better alignment
        gbc.anchor = GridBagConstraints.NORTH;  // Anchor to the top of the space
        gbc.insets = new Insets(10, 10, 5, 10);  // Margin settings

        // Adding components to the right panel
        gbc.gridy = 0;
        rightPanel.add(dimensionPanel, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;  // Do not stretch the button horizontally
        gbc.anchor = GridBagConstraints.CENTER;  // Center the button
        rightPanel.add(setButton, gbc);

        // Vertical filler to push everything to the top
        JPanel filler = new JPanel();
        gbc.weighty = 1;  // Give the filler all extra vertical space
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridy++;
        rightPanel.add(filler, gbc);

        rightPanel.revalidate();
        rightPanel.repaint();
    }
}
