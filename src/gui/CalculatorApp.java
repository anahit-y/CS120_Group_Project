package gui;

import javax.swing.*;
import java.awt.*;
import gui.matrixoperations.*;
import gui.vectoroperations.*;

public class CalculatorApp extends JFrame {
    public CalculatorApp() {
        setTitle("Matrix and Vector Buttons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        JPanel rightPanel = new JPanel();

        add(buttonPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        MatrixButton matrixButton = new MatrixButton(buttonPanel, rightPanel, "Matrix");
        matrixButton.setBackground(Color.DARK_GRAY);
        matrixButton.setForeground(Color.WHITE);
        matrixButton.setFocusPainted(false);
        VectorButton vectorButton = new VectorButton(buttonPanel, rightPanel, "Vector");
        vectorButton.setBackground(Color.GRAY);
        vectorButton.setForeground(Color.WHITE);
        vectorButton.setFocusPainted(false);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(matrixButton);
        topPanel.add(vectorButton);

        add(topPanel, BorderLayout.NORTH);

        matrixButton.addActionListener(matrixButton);
        vectorButton.addActionListener(vectorButton);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorApp gui = new CalculatorApp();
            gui.setVisible(true);
        });
    }
}
