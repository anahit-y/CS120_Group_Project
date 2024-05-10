package functionality.matrices;
import functionality.vectors.Vector;

public class IdentityMatrix extends AbstractSquareMatrix{
    // constructor
    public IdentityMatrix(int size){
        super(size);
    }

    // methods inherited from Matrix interface
    public Vector rowToVector(int rowNumber) {
        double[] elements = new double[getSize()];
        elements[rowNumber] = 1;
        return new Vector(elements);
    }

    public Vector columnToVector(int columnNumber) {
        double[] elements = new double[getSize()];
        elements[columnNumber] = 1;
        return new Vector(elements);
    }

    public Matrix multiply(Matrix m) throws IllegalArgumentException{
        if (this.getSize() != m.getNumberOfRows())
            throw new IllegalArgumentException();
        return (m);
    }

    public AbstractSquareMatrix scalarMultiply(double c){
        double[] m = new double[getSize()];
        if (c == 1)
            return this;
        for (int i = 0; i < m.length; i++)
            m[i] = c;
        return new DiagonalMatrix(m);
    }

    public IdentityMatrix transpose(){
        return this;
    }

    public double getElementAt(int i, int j){
        if (i == j)
            return 1;
        return 0;
    }

    // methods inherited from AbstractSquareMatrix
    public double getTrace(){
        return getSize();
    }
    public double getDeterminant(){
        return 1;
    }
    public boolean isInvertible(){
        return true;
    }
    public IdentityMatrix inverse(){
        return this;
    }
}