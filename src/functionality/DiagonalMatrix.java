package functionality;

public class DiagonalMatrix extends AbstractSquareMatrix {
    private double[] diagonal;

    // constructors
    public DiagonalMatrix(int size){
        super(size);
        this.diagonal = new double[size];
    }
    public DiagonalMatrix(double[] diagonal) {
        super(diagonal.length);
        this.diagonal = diagonal;
    }
    public DiagonalMatrix(DiagonalMatrix m){
        this(m.diagonal);
    }

    // methods inherited from Matrix interface
    public Vector rowToVector(int rowNumber) {
        double[] elements = new double[getSize()];
        elements[rowNumber] = diagonal[rowNumber];
        return new Vector(elements);
    }

    public Vector columnToVector(int columnNumber) {
        double[] elements = new double[getSize()];
        elements[columnNumber] = diagonal[columnNumber];
        return new Vector(elements);
    }


    public double getElementAt(int i, int j) {
        if (i == j)
            return diagonal[i];
        return 0;
    }

    public Matrix multiply(Matrix m) throws IllegalArgumentException {
        if (this.getSize() != m.getNumberOfRows())
            throw new IllegalArgumentException("Matrices can't be multiplied");

        GeneralMatrix newMatrix = new GeneralMatrix(this.getSize(), m.getNumberOfColumns());
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < m.getNumberOfColumns(); j++) {
                newMatrix.setElementAt(i, j, this.diagonal[i] * m.getElementAt(i, j));
            }
        }
        return newMatrix;
    }

    public AbstractSquareMatrix scalarMultiply(double c) {
        double[] newDiagonal = new double[getSize()];
        for (int i = 0; i < getSize(); i++) {
            newDiagonal[i] = this.diagonal[i] * c;
        }
        return new DiagonalMatrix(newDiagonal);
    }

    public AbstractSquareMatrix transpose() {
        return this;
    }

    // overriding methods inherited from AbstractSquareMatrix class
    public double getDeterminant() {
        double determinant = 1;
        for (int i = 0; i < getSize(); i++) {
            determinant *= diagonal[i];
        }
        return determinant;
    }

    public boolean isInvertible() {
        for (double element : diagonal) {
            if (element == 0) {
                return false;
            }
        }
        return true;
    }

    public DiagonalMatrix inverse() {
        if (!isInvertible()) {
            throw new ArithmeticException("Matrix is not invertible");
        }
        double[] inverseDiagonal = new double[getSize()];
        for (int i = 0; i < getSize(); i++) {
            inverseDiagonal[i] = 1 / diagonal[i];
        }
        return new DiagonalMatrix(inverseDiagonal);
    }
}
