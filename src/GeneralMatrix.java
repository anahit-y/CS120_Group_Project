package matrices;

import vectors.Vector;

public class GeneralMatrix implements Matrix, Cloneable{
    // instance variables
    private double[][] matrix;
    private int numberOfRows;
    private int numberOfColumns;

    // constructors
    public GeneralMatrix(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.matrix = new double[numberOfRows][numberOfColumns];
        int maxRange = 3 * Math.max(numberOfRows, numberOfColumns);

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                this.matrix[i][j] = (int) (Math.random() * maxRange);
            }
        }
    }

    public GeneralMatrix(double[][] matrix){
        this.matrix = matrix.clone();
        this.numberOfRows = matrix.length;
        this.numberOfColumns = matrix[0].length;
    }

    public GeneralMatrix(GeneralMatrix other){
        this(other.matrix);
    }

    // accessors
    public int getNumberOfRows(){
        return this.numberOfRows;
    }
    public int getNumberOfColumns(){
        return this.numberOfColumns;
    }
    public double getElementAt(int i, int j){
        return this.matrix[i][j];
    }

    public Vector rowToVector(int numberOfRow){
        return new Vector(this.matrix[numberOfRow]);
    }

    public Vector columnToVector(int numberOfColumn){
        double[] v = new double[this.getNumberOfColumns()];
        for (int i = 0; i < v.length; i++){
            v[i] = this.matrix[i][numberOfColumn];
        }
        return new Vector(v);
    }

    // operations on matrices
    public static GeneralMatrix add(GeneralMatrix m1, GeneralMatrix m2) {
        GeneralMatrix result = new GeneralMatrix(m1.numberOfRows, m1.numberOfColumns);
        for (int i = 0; i < m1.numberOfRows; i++) {
            for (int j = 0; j < m1.numberOfColumns; j++) {
                result.matrix[i][j] = m1.matrix[i][j] + m2.matrix[i][j];
            }
        }
        return result;
    }

    public static GeneralMatrix subtract(GeneralMatrix m1, GeneralMatrix m2) {
        GeneralMatrix result = new GeneralMatrix(m1.numberOfRows, m1.numberOfColumns);
        for (int i = 0; i < m1.numberOfRows; i++) {
            for (int j = 0; j < m1.numberOfColumns; j++) {
                result.matrix[i][j] = m1.matrix[i][j] - m2.matrix[i][j];
            }
        }
        return result;
    }

    public static GeneralMatrix multiply(GeneralMatrix m1, GeneralMatrix m2) {

        if (m1.numberOfColumns != m2.numberOfRows)
            return null;
        GeneralMatrix newMatrix = new GeneralMatrix(m1.numberOfRows, m2.numberOfColumns);
        if (newMatrix.numberOfRows == newMatrix.numberOfColumns){
            newMatrix = (SquareMatrix)newMatrix;
        }
        for (int i = 0; i < m1.numberOfRows; i++) {
            for (int j = 0; j < m2.numberOfColumns; j++) {
                newMatrix.matrix[i][j] = Vector.dotProduct(m1.rowToVector(i), m2.columnToVector(j));
            }
        }
        return newMatrix;
    }

    public static GeneralMatrix scalarMultiply(GeneralMatrix m, double c) {
        GeneralMatrix result = new GeneralMatrix(m.numberOfRows, m.numberOfColumns);
        for (int i = 0; i < m.numberOfRows; i++) {
            for (int j = 0; j < m.numberOfColumns; j++) {
                result.matrix[i][j] = m.matrix[i][j] * c;
            }
        }
        return result;
    }

    public static GeneralMatrix transpose(GeneralMatrix m){
        GeneralMatrix transposed = new GeneralMatrix(m.numberOfColumns, m.numberOfRows);
        for (int i = 0; i < m.numberOfRows; i++) {
            for (int j = 0; j < m.numberOfColumns; j++) {
                transposed.matrix[j][i] = m.matrix[i][j];
            }
        }
        return transposed;
    }

    // overridden methods inherited from Object

    public boolean equals(Object other){
        if (other == null)
            return false;
        else if (getClass() != other.getClass())
            return false;
        else {
            GeneralMatrix otherMatrix = (GeneralMatrix)other;
            if (this.numberOfRows != otherMatrix.numberOfRows ||
                    this.numberOfColumns != otherMatrix.numberOfColumns)
                return false;
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    if (this.matrix[i][j] != otherMatrix.matrix[i][j])
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

    public GeneralMatrix clone(){
        try{
            GeneralMatrix copy = (GeneralMatrix)super.clone();
            return copy;
        }
        catch (CloneNotSupportedException e){
            return null;
        }
    }

}
