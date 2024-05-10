package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.matrixoperations.*;

public class MatrixButton extends JButton implements ActionListener {
    private JPanel buttonPanel;
    private JPanel rightPanel; // This is the panel that each button will manipulate

    public MatrixButton(JPanel buttonPanel, JPanel rightPanel, String name) {
        super(name);
        this.buttonPanel = buttonPanel;
        this.rightPanel = rightPanel; // Store the reference to the right panel
    }


    public void actionPerformed(ActionEvent e) {
        rightPanel.removeAll();  // Clear previous components
        buttonPanel.removeAll();
        // Create instances of operation buttons, passing rightPanel to each
        MatrixAddition add = new MatrixAddition("Addition", rightPanel);
        add.configurations();
        MatrixSubtraction sub = new MatrixSubtraction("Subtraction", rightPanel);
        sub.configurations();
        MatrixMultiplication mult = new MatrixMultiplication("Multiplication", rightPanel);
        mult.configurations();
        MatrixScalar scalar = new MatrixScalar("Scalar Multiplication", rightPanel);
        scalar.configurations();
        Transpose transpose = new Transpose("Transpose", rightPanel);
        transpose.configurations();
        Determinant det = new Determinant("Determinant", rightPanel);
        det.configurations();
        Inverse inverse = new Inverse("Inverse", rightPanel);
        inverse.configurations();
        RowEchelon rowEch = new RowEchelon("Row Echelon", rightPanel);
        rowEch.configurations();
        ReducedRowEchelon rrowEch = new ReducedRowEchelon("Reduced Row Echelon", rightPanel);
        rrowEch.configurations();


        // Add these buttons to the button panel
        buttonPanel.add(add);
        add.addActionListener(k -> add.openPanel());
        buttonPanel.add(sub);
        sub.addActionListener(k -> sub.openPanel());
        buttonPanel.add(mult);
        mult.addActionListener(k -> mult.openPanel());
        buttonPanel.add(scalar);
        scalar.addActionListener(k -> scalar.openPanel());
        buttonPanel.add(transpose);
        transpose.addActionListener(k -> transpose.openPanel());
        buttonPanel.add(det);
        det.addActionListener(k -> det.openPanel());
        buttonPanel.add(inverse);
        inverse.addActionListener(k -> inverse.openPanel());
        buttonPanel.add(rowEch);
        rowEch.addActionListener(k -> rowEch.openPanel());
        buttonPanel.add(rrowEch);
        rrowEch.addActionListener(k -> rrowEch.openPanel());

        buttonPanel.revalidate();
        buttonPanel.repaint();

    }
}
