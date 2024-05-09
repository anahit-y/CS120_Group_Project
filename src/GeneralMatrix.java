package matrices;

import vectors.Vector;

public class GeneralMatrix implements Matrix{
    // instance variables
    private double[][] matrix;
    private int numberOfRows;
    private int numberOfColumns;

    // constructors
    public GeneralMatrix(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.matrix = new double[numberOfRows][numberOfColumns];
    }

    public GeneralMatrix(double[][] matrix) {
        this.matrix = matrix.clone();
        this.numberOfRows = matrix.length;
        this.numberOfColumns = matrix[0].length;
    }

    public GeneralMatrix(GeneralMatrix other) {
        this(other.matrix);
    }

    // accessors
    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    public double getElementAt(int numberOfRow, int numberOfColumn) {
        if (numberOfRow < 0 || numberOfRow >= this.numberOfRows ||
                numberOfColumn < 0 ||  numberOfColumn >= this.numberOfColumns)
            throw new IllegalArgumentException("Invalid index");
        return this.matrix[numberOfRow][numberOfColumn];
    }

    public Vector rowToVector(int numberOfRow) {
        if (numberOfRow < 0 || numberOfRow >= this.numberOfRows)
            throw new IllegalArgumentException("Invalid index");
        return new Vector(this.matrix[numberOfRow]);
    }

    public Vector columnToVector(int numberOfColumn) {
        if (numberOfColumn < 0 ||  numberOfColumn >= this.numberOfColumns)
            throw new IllegalArgumentException("Invalid index");
        double[] v = new double[this.numberOfRows];
        for (int i = 0; i < v.length; i++){
            v[i] = this.matrix[i][numberOfColumn];
        }
        return new Vector(v);
    }

    // operations on matrices
    public GeneralMatrix add(Matrix m) throws IllegalArgumentException{
        if (!(Matrix.equalSize(this, m)))
            throw new IllegalArgumentException();
        GeneralMatrix result = new GeneralMatrix(m.getNumberOfRows(), m.getNumberOfColumns());
        for (int i = 0; i < this.numberOfRows; i++) {
            for (int j = 0; j < this.numberOfColumns; j++) {
                result.matrix[i][j] = this.getElementAt(i, j) + m.getElementAt(i, j);
            }
        }
        return result;
    }

    public GeneralMatrix subtract(Matrix m) throws IllegalArgumentException{
        if (!(Matrix.equalSize(this, m)))
            throw new IllegalArgumentException();
        GeneralMatrix result = new GeneralMatrix(m.getNumberOfRows(), m.getNumberOfColumns());
        for (int i = 0; i < this.numberOfRows; i++) {
            for (int j = 0; j < this.numberOfColumns; j++) {
                result.matrix[i][j] = this.getElementAt(i, j) - m.getElementAt(i, j);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix m) throws IllegalArgumentException{
        if (this.numberOfColumns != m.getNumberOfRows())
            throw new IllegalArgumentException("Matrices can't be multiplied");
        GeneralMatrix result = new GeneralMatrix(this.numberOfRows, m.getNumberOfColumns());
        for (int i = 0; i < this.getNumberOfRows(); i++) {
            for (int j = 0; j < m.getNumberOfColumns(); j++) {
                result.matrix[i][j] = Vector.dotProduct(this.rowToVector(i), m.columnToVector(j));
            }
        }
        return result;
    }

    public void setElementAt(int i, int j, double value){
        this.matrix[i][j] = value;
    }

    public GeneralMatrix scalarMultiply(double c){
        GeneralMatrix result = new GeneralMatrix(this);
        for (int i = 0; i < this.numberOfRows; i++) {
            for (int j = 0; j < this.numberOfColumns; j++) {
                result.matrix[i][j] *= c;
            }
        }
        return result;
    }

    public GeneralMatrix transpose() {
        GeneralMatrix result = new GeneralMatrix(this.numberOfColumns, this.numberOfRows);
        for (int i = 0; i < this.numberOfRows; i++) {
            for (int j = 0; j < this.numberOfColumns; j++) {
                result.matrix[j][i] = this.matrix[i][j];
            }
        }
        return result;
    }

    // overridden methods inherited from Object

    public boolean equals(Object other){
        if (other == null)
            return false;
        else if (!(other instanceof Matrix))
            return false;
        else {
            Matrix otherMatrix = (Matrix)other;
            if (!(Matrix.equalSize(this, otherMatrix)))
                return false;
            for (int i = 0; i < this.getNumberOfRows(); i++) {
                for (int j = 0; j < this.getNumberOfColumns(); j++) {
                    if (this.getElementAt(i,j) != otherMatrix.getElementAt(i,j))
                        return false;
                }
            }
            return true;
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numberOfRows; i++) {
            for (int j = 0; j < this.numberOfColumns; j++) {
                sb.append(this.matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
