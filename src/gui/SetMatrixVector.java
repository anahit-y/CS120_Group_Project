package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SetMatrixVector extends JButton {
    String operationName;
    private JFrame frameA;
    private JFrame frameB;

    public SetMatrixVector(String name) {
        super(name);
    }

    public void openTwoWindows(int rows, int cols, String type, String operationName) {
        this.operationName = operationName;

        // Calculate dynamic width and height
        int width = Math.max(cols * 70 + 40, 300); // Ensure minimum width of 300
        int height = Math.max(rows * 70 + 100, 400); // Ensure minimum height of 400

        frameA = createMatrixWindow(type + " A Input", rows, cols, width, height);
        frameB = createMatrixWindow(type + " B Input", rows, cols, width, height);

        frameA.setLocation(100, 100);
        frameB.setLocation(frameA.getX() + frameA.getWidth() + 20, frameA.getY());

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
        calculateButton.setPreferredSize(new Dimension(120, 30));
        calculateButton.addActionListener(e -> {
            disposeOpenFrames(); // Dispose old frames
            if (type.equals("Vector")) {
                Calculate calculate = new Calculate(frameA, frameB, operationName, rows, cols, rows, cols);
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

        // Calculate dynamic width and height
        int widthA = Math.max(mid * 70 + 40, 300); // Ensure minimum width of 300
        int heightA = Math.max(rows * 70 + 100, 400); // Ensure minimum height of 400
        int widthB = Math.max(col * 70 + 40, 300); // Ensure minimum width of 300
        int heightB = Math.max(mid * 70 + 100, 400); // Ensure minimum height of 400

        frameA = createMatrixWindow(type + " A Input", rows, mid, widthA, heightA);
        frameB = createMatrixWindow(type + " B Input", mid, col, widthB, heightB);

        frameA.setLocation(100, 100);
        frameB.setLocation(frameA.getX() + frameA.getWidth() + 20, frameA.getY());

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
        calculateButton.setPreferredSize(new Dimension(120, 30));
        calculateButton.addActionListener(e -> {
            disposeOpenFrames(); // Dispose old frames
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

    public void openScalarWindow(int rows, int cols, String type, String operationName, double scalar) {
        this.operationName = operationName;

        // Calculate dynamic width and height
        int width = Math.max(cols * 70 + 40, 300); // Ensure minimum width of 300
        int height = Math.max(rows * 70 + 100, 400); // Ensure minimum height of 400

        frameA = createMatrixWindow(type + " Input", rows, cols, width, height);

        frameA.setLocationRelativeTo(null);
        frameA.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(Color.DARK_GRAY);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setPreferredSize(new Dimension(120, 30));
        calculateButton.addActionListener(e -> {
            Calculate calculate = new Calculate(frameA, operationName, rows, cols, scalar);
            if (operationName.equals("VScalar")) {
                calculate.performVectorCalculation();
            } else if (operationName.equals("Scalar")) {
                calculate.performMatrixCalculation();
            }

            disposeOpenFrames(); // Dispose old frames
        });

        JPanel buttonPanelA = (JPanel) frameA.getContentPane().getComponent(1);
        buttonPanelA.add(calculateButton);

        frameA.setVisible(true);
    }

    public void openOneWindow(int rows, int cols, String type, String operationName) {
        this.operationName = operationName;

        // Calculate dynamic width and height
        int width = Math.max(cols * 70 + 40, 300); // Ensure minimum width of 300
        int height = Math.max(rows * 70 + 100, 400); // Ensure minimum height of 400

        frameA = createMatrixWindow(type + " Input", rows, cols, width, height);

        frameA.setLocationRelativeTo(null);
        frameA.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(Color.DARK_GRAY);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setPreferredSize(new Dimension(120, 30));
        calculateButton.addActionListener(e -> {
            disposeOpenFrames(); // Dispose old frames
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

        frame.setMinimumSize(new Dimension(width, height)); // Set minimum size

        JPanel matrixPanel = new JPanel(new GridBagLayout());
        matrixPanel.setBackground(Color.DARK_GRAY);
        JTextField[][] matrix = new JTextField[rows][cols];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.NONE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new JTextField(5);
                matrix[i][j].setHorizontalAlignment(JTextField.CENTER);
                matrix[i][j].setPreferredSize(new Dimension(50, 50));
                gbc.gridx = j;
                gbc.gridy = i;
                matrixPanel.add(matrix[i][j], gbc);
            }
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.DARK_GRAY);
        JButton setZerosButton = new JButton("Set Zeros");
        JButton clearButton = new JButton("Clear");

        setZerosButton.setBackground(Color.DARK_GRAY);
        setZerosButton.setForeground(Color.WHITE);
        setZerosButton.setFocusPainted(false);
        setZerosButton.setPreferredSize(new Dimension(120, 30));

        clearButton.setBackground(Color.DARK_GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setPreferredSize(new Dimension(120, 30));

        setZerosButton.addActionListener(e -> {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j].getText().isEmpty()) {
                        matrix[i][j].setText("0");
                    }
                }
            }
        });

        clearButton.addActionListener(e -> {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j].setText("");
                }
            }
        });

        buttonPanel.add(setZerosButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(clearButton);
        frame.add(matrixPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Add matrix options menu if rows and cols are equal
        if (rows == cols) {
            addMatrixOptionsMenu(frame, matrix);
        }

        return frame;
    }

    private void addMatrixOptionsMenu(JFrame frame, JTextField[][] matrix) {
        JMenuBar menuBar = new JMenuBar();
        JMenu matrixMenu = new JMenu("Matrix Options");

        JMenuItem symmetricItem = new JMenuItem("Symmetric Matrix");
        symmetricItem.addActionListener(e -> {
            makeSymmetric(matrix);
        });

        JMenuItem diagonalItem = new JMenuItem("Diagonal Matrix");
        diagonalItem.addActionListener(e -> {
            makeDiagonal(matrix);
        });

        JMenuItem identityItem = new JMenuItem("Identity Matrix");
        identityItem.addActionListener(e -> {
            makeIdentity(matrix);
        });

        matrixMenu.add(symmetricItem);
        matrixMenu.add(diagonalItem);
        matrixMenu.add(identityItem);
        menuBar.add(matrixMenu);

        frame.setJMenuBar(menuBar);
    }

    private void makeSymmetric(JTextField[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i < j) {
                    int row = i, col = j;
                    matrix[row][col].addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            matrix[col][row].setText(matrix[row][col].getText());
                        }
                    });
                }
            }
        }
    }

    private void makeDiagonal(JTextField[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    matrix[i][j].setText("0");
                } else {
                    matrix[i][j].setText("");
                }
            }
        }
    }

    private void makeIdentity(JTextField[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    matrix[i][j].setText("1");
                } else {
                    matrix[i][j].setText("0");
                }
            }
        }
    }

    private void disposeOpenFrames() {
        if (frameA != null) {
            frameA.dispose();
        }
        if (frameB != null) {
            frameB.dispose();
        }
    }
}