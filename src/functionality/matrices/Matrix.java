package functionality.matrices;
import functionality.vectors.Vector;

public interface Matrix{

    // operations for vectors
    public Vector rowToVector(int numberOfRow);
    public Vector columnToVector(int numberOfColumn);

    // accessors
    public int getNumberOfRows();
    public int getNumberOfColumns();
    public double getElementAt(int numberOfRow, int numberOfColumn);

    // operations on matrices
    public Matrix add(Matrix m) throws IllegalArgumentException;

    public Matrix subtract(Matrix m) throws IllegalArgumentException;

    public Matrix multiply(Matrix m) throws IllegalArgumentException;

    public Matrix scalarMultiply(double c);

    public Matrix transpose();


    // helper method
    public static boolean equalSize(Matrix m1, Matrix m2){
        return ((m1.getNumberOfRows() == m2.getNumberOfRows())
                && (m1.getNumberOfColumns() == m2.getNumberOfColumns()));
    }
}
