package src.gui.matrixoperations;
import gui.*;

import javax.swing.*;
import java.awt.*;

public class MatrixMultiplication extends JButton implements OperationButton {
    private JPanel rightPanel; // Panel to update

    public MatrixMultiplication(String name, JPanel panel) {
        super(name);
        this.rightPanel = panel; // Store the reference to the right panel
    }
    public void configurations(){
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
    }
    public void openPanel() {
        rightPanel.removeAll();  // Clear previous components

        // Panel for Matrix A dimensions
        JLabel labelA = new JLabel("Matrix A dimension:");
        JTextField fieldA1 = new JTextField("1", 5);  // Default value 1
        JTextField fieldA2 = new JTextField("1", 5);  // Default value 1

        JPanel dimensionPanelA = new JPanel();
        dimensionPanelA.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        dimensionPanelA.add(labelA);
        dimensionPanelA.add(fieldA1);
        dimensionPanelA.add(new JLabel("x"));
        dimensionPanelA.add(fieldA2);

        // Panel for Matrix B dimensions
        JLabel labelB = new JLabel("Matrix B dimension:");
        JTextField fieldB1 = new JTextField("1", 5);  // Default value 1
        JTextField fieldB2 = new JTextField("1", 5);  // Default value 1
        JPanel dimensionPanelB = new JPanel();
        dimensionPanelB.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        dimensionPanelB.add(labelB);
        dimensionPanelB.add(fieldB1);
        dimensionPanelB.add(new JLabel("x"));
        dimensionPanelB.add(fieldB2);

        fieldA2.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                update();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                update();
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                update();
            }
            private void update() {
                fieldB1.setText(fieldA2.getText()); // Update fieldB1 when fieldA2 changes
            }
        });

        // Set Matrices button
        SetMatrixVector setButton = new SetMatrixVector("Set Matrices");
        setButton.setBackground(Color.DARK_GRAY);
        setButton.setForeground(Color.WHITE);
        setButton.setFocusPainted(false);
        setButton.addActionListener(k -> {
            try {
                int dimensionA1 = Integer.parseInt(fieldA1.getText());
                int dimensionA2 = Integer.parseInt(fieldA2.getText());
                int dimensionB2 = Integer.parseInt(fieldB2.getText());
                setButton.openTwoWindows(dimensionA1, dimensionA2, dimensionB2, "Matrix", "Multiplication");
            } catch (NumberFormatException e) {
                // Handle error if the text fields contain non-numeric values
                System.out.println("Please enter valid numbers in the dimension fields.");
            }
        });

        // Using GridBagLayout for main panel layout
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;    // Align components in the first column
        gbc.gridwidth = GridBagConstraints.REMAINDER;  // End row
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;  // Anchor to the top of the space
        gbc.insets = new Insets(10, 10, 10, 10);  // Margin settings

        // Adding components to the right panel
        gbc.gridy = 0;
        rightPanel.add(dimensionPanelA, gbc);
        gbc.gridy++;
        rightPanel.add(dimensionPanelB, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
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
