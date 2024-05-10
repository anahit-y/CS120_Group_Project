package gui.vectoroperations;

import gui.*;

import javax.swing.*;
import java.awt.*;

public class CrossProduct extends JButton implements OperationButton {
    private JPanel rightPanel; // Panel to update

    public CrossProduct(String name, JPanel panel) {
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
        JLabel label = new JLabel("Vector size:");
        JTextField field1 = new JTextField("3", 5);  // Pre-filled with 3
        field1.setEditable(false);  // Make it non-editable
        SetMatrixVector setButton = new SetMatrixVector("Set Vector");
        setButton.addActionListener(k -> {
                int dimension1 = Integer.parseInt(field1.getText()); // Get number from field1
                setButton.openTwoWindows(1, dimension1, "Vector", "Cross");
        });
        // Set some optional styles (you can customize these)
        setButton.setBackground(Color.DARK_GRAY);
        setButton.setForeground(Color.WHITE);
        setButton.setFocusPainted(false);
        field1.setHorizontalAlignment(JTextField.CENTER);
        // field2.setHorizontalAlignment(JTextField.CENTER);

        // Panel for dimensions with a label and text fields
        JPanel dimensionPanel = new JPanel();
        dimensionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        dimensionPanel.add(label);
        dimensionPanel.add(field1);

        // Adding components to the main panel using GridBagLayout for precise positioning
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Positioning adjustments for dimension panel
        gbc.gridx = 0;    // Align components in the first column
        gbc.gridy = 0;    // Start from the first row
        gbc.gridwidth = GridBagConstraints.REMAINDER;  // End row
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;  // Anchor to the top of the space
        gbc.weightx = 1;  // Distribute extra horizontal space
        gbc.insets = new Insets(10, 10, 10, 10);  // Margin settings

        rightPanel.add(dimensionPanel, gbc);

        // Adjustments for "Set Matrices" button
        gbc.gridy++;  // Increment row for the next component
        gbc.fill = GridBagConstraints.NONE;  // Do not stretch horizontally
        gbc.anchor = GridBagConstraints.CENTER;  // Center the button
        rightPanel.add(setButton, gbc);

        // Optional: Placeholder panel for displaying answers later
        JPanel answerDisplayPanel = new JPanel();
        answerDisplayPanel.setPreferredSize(new Dimension(200, 100)); // Set preferred size
        gbc.gridy++;  // Increment row for the answer display
        rightPanel.add(answerDisplayPanel, gbc);

        // Vertical filler to push everything to the top
        gbc.weighty = 1;  // Give the filler all extra vertical space
        gbc.fill = GridBagConstraints.VERTICAL;  // Fill vertically
        JPanel filler = new JPanel();
        gbc.gridy++;  // Increment row for the filler
        rightPanel.add(filler, gbc);

        rightPanel.revalidate();
        rightPanel.repaint();
    }
}