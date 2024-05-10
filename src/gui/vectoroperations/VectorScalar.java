package gui.vectoroperations;

import gui.*;
import javax.swing.*;
import java.awt.*;

public class VectorScalar extends JButton implements OperationButton {
    private JPanel rightPanel; // Panel to update

    public VectorScalar(String name, JPanel panel) {
        super(name);
        this.rightPanel = panel; // Store the reference to the right panel
    }
    public void configurations(){
        this.setBackground(Color.GRAY);
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
    }
    public void openPanel() {
        rightPanel.removeAll();  // Clear previous components

        // Styling components
        JLabel label = new JLabel("Vector Size:");
        JTextField field = new JTextField("2", 5);  // Default value 2
        JLabel scalarLabel = new JLabel("Scalar Multiplier:");
        JTextField scalarField = new JTextField("1", 5);  // Default scalar value 1

        // Set Matrices button
        SetMatrixVector setButton = new SetMatrixVector("Set Vector");
        double scalar = Double.parseDouble(scalarField.getText());
        setButton.addActionListener(k -> {
            try {
                int dimension1 = Integer.parseInt(field.getText()); // Get number from field1
                setButton.openScalarWindow(1, dimension1, "Vector", "Scalar", scalar);
            } catch (NumberFormatException e) {
                // Handle error if the text fields contain non-numeric values
                System.out.println("Please enter valid numbers in the dimension fields.");
            }
        });
        setButton.setBackground(Color.DARK_GRAY);
        setButton.setForeground(Color.WHITE);
        setButton.setFocusPainted(false);

        // Panel for matrix dimension and scalar multiplier
        JPanel dimensionPanel = new JPanel();
        dimensionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        dimensionPanel.add(label);
        dimensionPanel.add(field);
        dimensionPanel.add(scalarLabel);
        dimensionPanel.add(scalarField);

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
        gbc.fill = GridBagConstraints.VERTICAL;  // Fill vertically
        gbc.gridy++;
        rightPanel.add(filler, gbc);

        rightPanel.revalidate();
        rightPanel.repaint();
    }
}