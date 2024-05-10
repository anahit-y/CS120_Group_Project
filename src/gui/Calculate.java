package gui;

import functionality.*;
import javax.swing.*;
import java.awt.*;

public class Calculate extends JButton{
    private JFrame frameA;
    private JFrame frameB;
    private String operationName;
    private int rowsA, colsA, rowsB, colsB;
    private double scalar;
    private double[] vectorA, vectorB;

    // Constructor for one window
    public Calculate(String name){
        super(name);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
    }
    public Calculate(JFrame frameA, String operationName, int rowsA, int colsA) {
        this.frameA = frameA;
        this.frameB = null;
        this.operationName = operationName;
        this.rowsA = rowsA;
        this.colsA = colsA;
    }

    // Constructor for two windows
    public Calculate(JFrame frameA, JFrame frameB, String operationName, int rowsA, int colsA, int rowsB, int colsB) {
        this.frameA = frameA;
        this.frameB = frameB;
        this.operationName = operationName;
        this.rowsA = rowsA;
        this.colsA = colsA;
        this.rowsB = rowsB;
        this.colsB = colsB;
    }

    // Constructor for scalar multiplication
    public Calculate(JFrame frameA, String operationName, int rowsA, int colsA, double scalar) {
        this.frameA = frameA;
        this.frameB = null;
        this.operationName = operationName;
        this.rowsA = rowsA;
        this.colsA = colsA;
        this.scalar = scalar;
    }

    // Constructor for vector operations
    public Calculate(double[] vectorA, double[] vectorB, String operationName) {
        this.vectorA = vectorA;
        this.vectorB = vectorB;
        this.operationName = operationName;
    }

    // Constructor for single vector operations
    public Calculate(double[] vectorA, String operationName) {
        this.vectorA = vectorA;
        this.operationName = operationName;
    }

    public void performCalculation() {
        if (vectorA != null && (vectorB != null || operationName.equals("Scalar"))) {
            callVectorCalculationMethod(vectorA, vectorB);
        } else {
            JPanel matrixPanelA = (JPanel) frameA.getContentPane().getComponent(0);
            double[][] matrixA = extractMatrix(matrixPanelA, rowsA, colsA);

            double[][] matrixB = null;
            if (frameB != null) {
                JPanel matrixPanelB = (JPanel) frameB.getContentPane().getComponent(0);
                matrixB = extractMatrix(matrixPanelB, rowsB, colsB);
            }

            if (matrixB == null && operationName.equals("Scalar")) {
                callCalculationMethod(matrixA, scalar);
            } else if (matrixB == null) {
                callCalculationMethod(matrixA);
            } else {
                callCalculationMethod(matrixA, matrixB);
            }

            frameA.dispose();
            if (frameB != null) {
                frameB.dispose();
            }
        }
    }

    private double[][] extractMatrix(JPanel panel, int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JTextField field = (JTextField) panel.getComponent(i * cols + j);
                matrix[i][j] = Double.parseDouble(field.getText());
            }
        }
        return matrix;
    }

    private void callCalculationMethod(double[][] matrixA, double[][] matrixB) {
        switch (operationName) {
            case "Addition":
                performAddition(matrixA, matrixB);
                break;
            case "Multiplication":
                performMultiplication(matrixA, matrixB);
                break;
            case "Subtraction":
                performSubtraction(matrixA, matrixB);
                break;
            // Add cases for other operations
        }
    }

    private void callCalculationMethod(double[][] matrixA) {
        switch (operationName) {
            case "Determinant":
                performDeterminant(matrixA);
                break;
            case "Transpose":
                performTranspose(matrixA);
                break;
            case "Inverse":
                performInverse(matrixA);
                break;
        }
    }

    private void callCalculationMethod(double[][] matrixA, double scalar) {
        if(operationName.equals("Scalar")){
            performScalar(matrixA, scalar);
        }
    }
    private void callVectorCalculationMethod(double[] vectorA, double[] vectorB) {
        switch (operationName) {
            case "Addition":
                performVectorAddition(vectorA, vectorB);
                break;
            case "Subtraction":
                performVectorSubtraction(vectorA, vectorB);
                break;
            case "Scalar":
                performVectorScalar(vectorA, scalar);
                break;
            case "Dot":
                performDotProduct(vectorA, vectorB);
                break;
            case "Cross":
                performCrossProduct(vectorA, vectorB);
                break;
            case "Distance":
                performDistance(vectorA, vectorB);
                break;
            case "Cosine":
                performCosine(vectorA, vectorB);
                break;
            case "Normalize":
                performNormalize(vectorA);
                break;
            case "Orthogonal":
                performOrthogonal(vectorA, vectorB);
                break;
            // Add cases for other vector operations
        }
    }

    // Placeholder methods for performing matrix operations, replace these with your actual methods
    private void performAddition(double[][] matrixA, double[][] matrixB) {
        GeneralMatrix matA = new GeneralMatrix(matrixA);
        GeneralMatrix matB = new GeneralMatrix(matrixB);
        GeneralMatrix result = matA.add(matB);
        displayResult(result.getMatrix());
    }

    private void performSubtraction(double[][] matrixA, double[][] matrixB) {
        GeneralMatrix matA = new GeneralMatrix(matrixA);
        GeneralMatrix matB = new GeneralMatrix(matrixB);
        GeneralMatrix result = matA.subtract(matB);
        displayResult(result.getMatrix());
    }

    private void performMultiplication(double[][] matrixA, double[][] matrixB) {
        GeneralMatrix matA = new GeneralMatrix(matrixA);
        GeneralMatrix matB = new GeneralMatrix(matrixB);
        GeneralMatrix result = (GeneralMatrix) matA.multiply(matB);
        displayResult(result.getMatrix());
    }

    private void performDeterminant(double[][] matrixA) {
        SquareMatrix matrix = new SquareMatrix(matrixA);
        displayResult(matrix.getDeterminant());
    }

    private void performTranspose(double[][] matrixA) {
        GeneralMatrix matrix = new GeneralMatrix(matrixA).transpose();
        displayResult(matrix.getMatrix());
    }

    private void performInverse(double[][] matrixA) {
        SquareMatrix matrix = new SquareMatrix(matrixA).inverse();
        displayResult(matrix.getMatrix());
    }

    private void performScalar(double[][] matrixA, double scalar) {
        GeneralMatrix matrix = new GeneralMatrix(matrixA).scalarMultiply(scalar);
        displayResult(matrix.getMatrix());
    }

    // vector operations
    private void performVectorAddition(double[] vectorA, double[] vectorB) {
        Vector vector1 = new Vector(vectorA);
        Vector vector2 = new Vector(vectorB);
        displayVectorResult(Vector.add(vector1,vector2).getVector());
    }
    private void performVectorSubtraction(double[] vectorA, double[] vectorB) {
        Vector vector1 = new Vector(vectorA);
        Vector vector2 = new Vector(vectorB);
        displayVectorResult(Vector.subtract(vector1,vector2).getVector());
    }
    private void performVectorScalar(double[] vectorA, double scalar) {
        Vector vector1 = new Vector(vectorA);
        displayVectorResult(Vector.scalarMultiply(vector1, scalar).getVector());
    }
    private void performCrossProduct(double[] vectorA, double[] vectorB) {
        Vector vector1 = new Vector(vectorA);
        Vector vector2 = new Vector(vectorB);
        displayVectorResult(Vector.crossProduct(vector1, vector2).getVector());
    }
    private void performNormalize(double[] vectorA) {
        Vector vector1 = new Vector(vectorA);
        displayVectorResult(vector1.normalize().getVector());
    }
    private void performDistance(double[] vectorA, double[] vectorB) {
        Vector vector1 = new Vector(vectorA);
        Vector vector2 = new Vector(vectorB);
        displayTextResult("Distance Result",Vector.distanceFrom(vector2,vector1));
    }
    private void performDotProduct(double[] vectorA, double[] vectorB) {
        Vector vector1 = new Vector(vectorA);
        Vector vector2 = new Vector(vectorB);
        displayTextResult("Dot Product Result",Vector.dotProduct(vector1, vector2));
    }
    private void performCosine(double[] vectorA, double[] vectorB) {
        Vector vector1 = new Vector(vectorA);
        Vector vector2 = new Vector(vectorB);
        displayTextResult("Cosine Result", Vector.cosineOfAngleOfTwoVectors(vector1, vector2));
    }
    private void performOrthogonal(double[] vectorA, double[] vectorB) {
        Vector vector1 = new Vector(vectorA);
        Vector vector2 = new Vector(vectorB);
        boolean checker = Vector.areOrthogonal(vector1, vector2);
        double result = checker? 1.0:0;
        displayTextResult("Cosine Result", result);
    }
    private void displayResult(double[][] result) {
        JFrame resultFrame = new JFrame("Result");
        resultFrame.setSize(300, 300);
        resultFrame.setLayout(new BorderLayout());

        JPanel resultPanel = new JPanel(new GridLayout(result.length, result[0].length, 10, 10));
        for (double[] row : result) {
            for (double value : row) {
                JTextField field = new JTextField(String.valueOf(value));
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setEditable(false);
                resultPanel.add(field);
            }
        }
        resultFrame.add(resultPanel, BorderLayout.CENTER);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setVisible(true);
    }

    private void displayResult(double result) {
        JFrame resultFrame = new JFrame("Result");
        resultFrame.setSize(200, 100);
        resultFrame.setLayout(new BorderLayout());

        JTextField resultField = new JTextField(String.valueOf(result));
        resultField.setHorizontalAlignment(JTextField.CENTER);
        resultField.setEditable(false);

        resultFrame.add(resultField, BorderLayout.CENTER);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setVisible(true);
    }
    private void displayVectorResult(double[] result) {
        JFrame resultFrame = new JFrame("Vector Result");
        resultFrame.setSize(300, 100);
        resultFrame.setLayout(new BorderLayout());

        JPanel resultPanel = new JPanel(new GridLayout(1, result.length, 10, 10));
        for (double value : result) {
            JTextField field = new JTextField(String.valueOf(value));
            field.setHorizontalAlignment(JTextField.CENTER);
            field.setEditable(false);
            resultPanel.add(field);
        }

        resultFrame.add(resultPanel, BorderLayout.CENTER);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setVisible(true);
    }
    private void displayTextResult(String title, double result) {
        JFrame resultFrame = new JFrame(title);
        resultFrame.setSize(300, 100);
        resultFrame.setLayout(new BorderLayout());

        String message = "";
        switch (title) {
            case "Orthogonality Result":
                message = result == 1.0 ? "The vectors are orthogonal." : "The vectors are not orthogonal.";
                break;
            case "Cosine Result":
                message = "Cosine: " + result;
                break;
            case "Dot Product Result":
                message = "Dot Product: " + result;
                break;
            case "Distance Result":
                message = "Distance Result is: " + result;
                break;
            default:
                message = "Result: " + result;
                break;
        }

        JLabel resultLabel = new JLabel(message, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        resultFrame.add(resultLabel, BorderLayout.CENTER);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setVisible(true);
    }

}
