package functionality;

public class SquareMatrix extends AbstractSquareMatrix {
    private double[][] matrix;

    // constructors
    public SquareMatrix(int size) {
        super(size);
        this.matrix = new double[size][size];
    }

    public SquareMatrix(double[][] squareMatrix) {
        super(squareMatrix.length);
        if (squareMatrix.length != squareMatrix[0].length)
            throw new IllegalArgumentException("Non square matrix");
        this.matrix = squareMatrix;
    }

    public SquareMatrix(SquareMatrix other) {
        this(other.matrix);
    }

    public SquareMatrix(Matrix other){
        super(other.getNumberOfRows());
        if (other.getNumberOfRows() != other.getNumberOfColumns())
            throw new IllegalArgumentException();
        this.matrix = new double[other.getNumberOfRows()][other.getNumberOfColumns()];
        for (int i = 0; i < other.getNumberOfRows(); i++){
            for (int j = 0; j < other.getNumberOfRows(); j++){
                this.matrix[i][j] = other.getElementAt(i, j);
            }
        }

    }

    public void setElement(int i, int j, double value){
        this.matrix[i][j] = value;
    }

// methods inherited from Matrix interface

    // operations for vectors
    public Vector rowToVector(int rowNumber) {
        return new Vector(this.matrix[rowNumber]);
    }

    public Vector columnToVector(int columnNumber) {
        double[] v = new double[this.getSize()];
        for (int i = 0; i < v.length; i++) {
            v[i] = this.matrix[i][columnNumber];
        }
        return new Vector(v);
    }

    // accessors
    public double getElementAt(int i, int j) {
        return this.matrix[i][j];
    }

    // operations on matrices
    public Matrix multiply(Matrix m) throws IllegalArgumentException{
        if (this.getNumberOfColumns() != m.getNumberOfRows())
            throw new IllegalArgumentException("Matrices can't be multiplied");
        GeneralMatrix result = new GeneralMatrix(this.getNumberOfRows(), m.getNumberOfColumns());
        for (int i = 0; i < this.getNumberOfRows(); i++) {
            for (int j = 0; j < m.getNumberOfColumns(); j++) {
                result.setElementAt(i, j, Vector.dotProduct(this.rowToVector(i), m.columnToVector(j)));
            }
        }
        return result;
    }

    public SquareMatrix scalarMultiply(double c){
        SquareMatrix result = new SquareMatrix(this.getSize());
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                result.matrix[i][j] = this.getElementAt(i, j) * c;
            }
        }
        return result;
    }

    public SquareMatrix transpose(){
        SquareMatrix result = new SquareMatrix(this.getSize());
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                result.matrix[i][j] = this.getElementAt(j, i);
            }
        }
        return result;
    }

    // methods from AbstractSquareMatrix class
    public double getDeterminant() {
        int size = this.getSize();
        // base cases
        if (size == 1) {
            return this.getElementAt(0, 0);
        } else if (size == 2) {
            return (this.getElementAt(0, 0) * this.getElementAt(1, 1) - this.getElementAt(0, 1) * this.getElementAt(1, 0));
        }

        double determinant = 0.0;
        for (int j = 0; j < size; j++) {
            determinant += this.getElementAt(0, j) * Math.pow(-1, j) * this.getMinorMatrix(0, j).getDeterminant();
        }
        return determinant;
    }

    public SquareMatrix inverse() {
        if (!isInvertible())
            throw new IllegalArgumentException("Matrix is not invertible.");

        SquareMatrix mat = new SquareMatrix(getSize());
        double determinant = getDeterminant();
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                double sign = ((i + j) % 2 == 0) ? 1 : -1; // Calculating sign based on row and column indices
                double cofactorValue = sign * getMinorMatrix(i, j).getDeterminant();
                mat.setElement(j, i, cofactorValue / determinant);
            }
        }
        return mat;
    }



    // helper methods for getDeterminant and inverse
    private boolean hasRowOfZeros() {
        for (int i = 0; i < this.getSize(); i++) {
            if (this.rowToVector(i).isZeroVector()) {
                return true;
            }
        }
        return false;
    }

    private boolean hasIdenticalRows() {
        for (int i = 0; i < this.getSize() - 1; i++) {
            for (int j = i + 1; j < this.getSize(); j++) {
                if (this.rowToVector(i).equals(this.rowToVector(j)))
                    return true;
            }
        }
        return false;
    }

    private SquareMatrix getMinorMatrix(int rowToRemove, int colToRemove) {
        int size = this.getSize();
        double[][] minorMatrix = new double[size - 1][size - 1];
        int newRow = 0;
        for (int i = 0; i < size; i++) {
            if (i == rowToRemove) {
                continue;
            }
            int newCol = 0;
            for (int j = 0; j < size; j++) {
                if (j == colToRemove) {
                    continue;
                }
                minorMatrix[newRow][newCol] = this.getElementAt(i, j);
                newCol++;
            }
            newRow++;
        }
        return new SquareMatrix(minorMatrix);
    }
}