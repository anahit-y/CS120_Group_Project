package gui.matrixoperations;

import gui.*;
import javax.swing.*;
import java.awt.*;

public class MatrixScalar extends JButton implements OperationButton {
    private JPanel rightPanel; // Panel to update

    public MatrixScalar(String name, JPanel panel) {
        super(name);
        this.rightPanel = panel; // Store the reference to the right panel
    }

    public void configurations() {
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
    }

    public void openPanel() {
        rightPanel.removeAll();  // Clear previous components

        // Styling components
        JLabel label = new JLabel("Matrix dimension:");
        JTextField field1 = new JTextField("2", 5);  // Default value for dimension 1
        JTextField field2 = new JTextField("2", 5);  // Default value for dimension 2
        JLabel xLabel = new JLabel("x");  // Label to denote the multiplication symbol
        JLabel scalarLabel = new JLabel("Scalar Multiplier:");
        JTextField scalarField = new JTextField("1", 5);  // Default scalar value 1

        // Set Matrices button
        SetMatrixVector setButton = new SetMatrixVector("Set Matrix");
        setButton.addActionListener(k -> {
            try {
                int dimension1 = Integer.parseInt(field1.getText());
                int dimension2 = Integer.parseInt(field2.getText());
                double scalar = Double.parseDouble(scalarField.getText());
                setButton.openScalarWindow(dimension1, dimension2, "Matrix", "Scalar",scalar);
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numbers in the dimension fields.");
            }
        });
        setButton.setBackground(Color.DARK_GRAY);
        setButton.setForeground(Color.WHITE);
        setButton.setFocusPainted(false);

        // Panel for matrix dimensions and scalar multiplier
        JPanel dimensionPanel = new JPanel();
        dimensionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        dimensionPanel.add(label);
        dimensionPanel.add(field1);
        dimensionPanel.add(xLabel);
        dimensionPanel.add(field2);
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
