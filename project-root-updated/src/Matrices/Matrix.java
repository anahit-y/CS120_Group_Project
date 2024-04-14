package Matrices;

import Exceptions.InvalidSizeException;
import Vectors.Vector;

public interface Matrix {

    Matrix scalarMultiply(double scalar);
    //boolean isEqual(Matrix other);
    int getNumberOfRows();
    int getNumberOfColumns();
    String toString();
}
