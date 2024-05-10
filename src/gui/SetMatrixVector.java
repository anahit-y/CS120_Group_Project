package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SetMatrixVector extends JButton {
    String operationName;

    public SetMatrixVector(String name) {
        super(name);
    }

    public void openTwoWindows(int rows, int cols, String type, String operationName) {
        this.operationName = operationName;
        int width = cols * 40 + 40;  // Cell width
        int height = rows * 40 + 40; // Cell height adjusted for text fields

        JFrame frameA = createMatrixWindow(type + " A Input", rows, cols, width, height);
        JFrame frameB = createMatrixWindow(type + " B Input", rows, cols, width, height);

        frameA.setLocationRelativeTo(null);
        frameB.setLocation(frameA.getX() + frameA.getWidth() + 10, frameA.getY());

        frameA.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frameB.dispose();
            }
        });

        frameB.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frameA.dispose();
            }
        });

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(Color.DARK_GRAY);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(e -> {
            if (type.equals("Vector")) {
                Calculate calculate = new Calculate(frameA, frameB, operationName, rows, cols,rows, cols);
                calculate.performVectorCalculation();
            } else {
                Calculate calculate = new Calculate(frameA, frameB, operationName, rows, cols, rows, cols);
                calculate.performMatrixCalculation();
            }
        });

        JPanel buttonPanelA = (JPanel) frameA.getContentPane().getComponent(1);
        buttonPanelA.add(calculateButton);

        frameA.setVisible(true);
        frameB.setVisible(true);
    }

    public void openTwoWindows(int rows, int mid, int col, String type, String operationName) {
        this.operationName = operationName;
        int width = mid * 40 + 40;
        int height1 = rows * 40 + 40;
        int height2 = col * 40 + 40;

        JFrame frameA = createMatrixWindow(type + " A Input", rows, mid, width, height1);
        JFrame frameB = createMatrixWindow(type + " B Input", mid, col, width, height2);

        frameA.setLocationRelativeTo(null);
        frameB.setLocation(frameA.getX() + frameA.getWidth() + 10, frameA.getY());

        frameA.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frameB.dispose();
            }
        });

        frameB.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frameA.dispose();
            }
        });
        Calculate calculateButton = new Calculate("Calculate");
        calculateButton.addActionListener(e -> {
            if (type.equals("Vector")) {
                double[] vectorA = extractVector(frameA, rows);
                double[] vectorB = extractVector(frameB, mid); // or some other logic based on vector operation
                Calculate calculate = new Calculate(vectorA, vectorB, operationName);
                calculate.performMatrixCalculation();
            } else {
                Calculate calculate = new Calculate(frameA, frameB, operationName, rows, mid, mid, col);
                calculate.performVectorCalculation();
            }
        });

        JPanel buttonPanelA = (JPanel) frameA.getContentPane().getComponent(1);
        buttonPanelA.add(calculateButton);

        frameA.setVisible(true);
        frameB.setVisible(true);
    }
    // For scalar multiplication
    public void openScalarWindow(int rows, int cols, String type, String operationName, double scalar) {
        this.operationName = operationName;
        int width = cols * 40 + 40;
        int height = rows * 40 + 40;

        JFrame frameA = createMatrixWindow(type + " Input", rows, cols, width, height);

        frameA.setLocationRelativeTo(null);
        frameA.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Calculate calculateButton = new Calculate("Calculate");
        calculateButton.addActionListener(e -> {
            Calculate calculate = new Calculate(frameA, operationName, rows, cols, scalar);
            calculate.performMatrixCalculation();
        });
        JPanel buttonPanelA = (JPanel) frameA.getContentPane().getComponent(1);
        buttonPanelA.add(calculateButton);

        frameA.setVisible(true);
    }
    public void openOneWindow(int rows, int cols, String type, String operationName) {
        this.operationName = operationName;
        int width = cols * 40 + 40;
        int height = rows * 40 + 40;

        JFrame frameA = createMatrixWindow(type + " Input", rows, cols, width, height);

        frameA.setLocationRelativeTo(null);
        frameA.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Calculate calculateButton = new Calculate("Calculate");
        calculateButton.addActionListener(e -> {
            if (type.equals("Vector")) {
                double[] vectorA = extractVector(frameA, rows);
                Calculate calculate = new Calculate(vectorA, operationName);
                calculate.performVectorCalculation();
            } else {
                Calculate calculate = new Calculate(frameA, operationName, rows, cols);
                calculate.performMatrixCalculation();
            }
        });

        JPanel buttonPanelA = (JPanel) frameA.getContentPane().getComponent(1);
        buttonPanelA.add(calculateButton);

        frameA.setVisible(true);
    }
    private double[] extractVector(JFrame frame, int size) {
        JPanel matrixPanel = (JPanel) frame.getContentPane().getComponent(0);
        double[] vector = new double[size];
        for (int i = 0; i < size; i++) {
            JTextField field = (JTextField) matrixPanel.getComponent(i);
            vector[i] = Double.parseDouble(field.getText());
        }
        return vector;
    }
    private JFrame createMatrixWindow(String title, int rows, int cols, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLayout(new BorderLayout());

        JPanel matrixPanel = new JPanel(new GridLayout(rows, cols, 10, 10));
        JTextField[][] matrix = new JTextField[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new JTextField();
                matrix[i][j].setHorizontalAlignment(JTextField.CENTER);
                matrixPanel.add(matrix[i][j]);
            }
        }
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton setZerosButton = new JButton("Set Zeros");
        JButton clearButton = new JButton("Clear");

        setZerosButton.setBackground(Color.DARK_GRAY);
        setZerosButton.setForeground(Color.WHITE);
        setZerosButton.setFocusPainted(false);

        clearButton.setBackground(Color.DARK_GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);

        // Action listener for Set Zeros button
        setZerosButton.addActionListener(e -> {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j].getText().isEmpty()) {
                        matrix[i][j].setText("0");
                    }
                }
            }
        });

        // Action listener for Clear button
        clearButton.addActionListener(e -> {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j].setText("");
                }
            }
        });

        buttonPanel.add(setZerosButton);
        buttonPanel.add(clearButton);

        frame.add(matrixPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        return frame;
    }
}
