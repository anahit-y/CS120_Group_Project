package CS120_Group_Project.src.functionality;

public class SymmetricMatrix extends AbstractSquareMatrix {
    // stores lower triangular matrix and elements on the diagonal
    private double[] data;

    // constructors
    public SymmetricMatrix(int size) {
        super(size);
        int dataSize = size * (size + 1) / 2;
        this.data = new double[dataSize];
    }

    public SymmetricMatrix(double[] data) {
        super((int) (Math.sqrt(8 * data.length + 1) - 1) / 2);
        this.data = data.clone();
    }

    // overriding methods inherited from Matrix interface
    public Vector rowToVector(int rowNumber) {
        double[] v = new double[this.getSize()];
        for (int i = 0; i < this.getSize(); i++) {
            v[i] = this.getElementAt(rowNumber, i);
        }
        return new Vector(v);
    }

    public Vector columnToVector(int columnNumber) {
        double[] v = new double[this.getSize()];
        for (int i = 0; i < this.getSize(); i++) {
            v[i] = this.getElementAt(i, columnNumber);
        }
        return new Vector(v);
    }


    public double getElementAt(int i, int j) {
        if (i < 0 || j < 0 || i >= getSize() || j >= getSize()) {
            throw new IndexOutOfBoundsException("Invalid matrix indices");
        }
        if (i >= j) {
            return data[i * (i + 1) / 2 + j];
        } else {
            return data[j * (j + 1) / 2 + i];
        }
    }

    public AbstractSquareMatrix transpose() {
        return this;
    }

    public Matrix multiply(Matrix m) {
        if (this.getSize() != m.getNumberOfRows()) {
            throw new IllegalArgumentException("Invalid matrix for multiplication");
        }
        GeneralMatrix result = new GeneralMatrix(this.getNumberOfRows(), m.getNumberOfColumns());
        for (int i = 0; i < result.getNumberOfRows(); i++) {
            for (int j = 0; j < result.getNumberOfColumns(); j++) {
                result.setElementAt(i, j, Vector.dotProduct(this.rowToVector(i), m.columnToVector(j)));
            }
        }
        return result;
    }

    public AbstractSquareMatrix scalarMultiply(double c) {
        SymmetricMatrix result = new SymmetricMatrix(this.data);
        for (int i = 0; i < this.data.length; i++)
            result.data[i] = c * data[i];
        return result;
    }

    private SquareMatrix toSquareMatrix(){
        SquareMatrix newMatrix = new SquareMatrix(getSize());
        for (int i = 0; i < getSize(); i++){
            for (int j = 0; j < getSize(); j++){
                newMatrix.setElement(i, j, this.getElementAt(i,j));
            }
        }
        return newMatrix;
    }

    public AbstractSquareMatrix inverse(){
        return toSquareMatrix().inverse();
    }

    public double getDeterminant(){
        return toSquareMatrix().getDeterminant();
    }
}