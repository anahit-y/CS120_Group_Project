package functionality;

public abstract class AbstractSquareMatrix implements Matrix {
    //instance variable
    private int size;

    // constructor
    public AbstractSquareMatrix(int size){
        this.size = size;
    }

    // overriding methods inherited from Matrix interface
    public int getNumberOfRows() {
        return this.size;
    }
    public int getNumberOfColumns() {
        return this.size;
    }
    public int getSize(){
        return this.size;
    }
    public SquareMatrix add(Matrix m) throws IllegalArgumentException{
        if (!(Matrix.equalSize(this, m)))
            throw new IllegalArgumentException();
        SquareMatrix result = new SquareMatrix(this.getSize());
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                result.setElement(i,j, this.getElementAt(i, j) + m.getElementAt(i, j));
            }
        }
        return result;
    }

    public SquareMatrix subtract(Matrix m) throws IllegalArgumentException{
        if (!(Matrix.equalSize(this, m)))
            throw new IllegalArgumentException();
        SquareMatrix result = new SquareMatrix(this.getSize());
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                result.setElement(i,j, this.getElementAt(i, j) - m.getElementAt(i, j));
            }
        }
        return result;
    }

    // methods for AbstractSquareMatrix class
    public double getTrace() {
        double trace = 0;
        for (int i = 0; i < this.getSize(); i++)
            trace += getElementAt(i,i);
        return trace;
    }
    public abstract AbstractSquareMatrix inverse();
    public abstract double getDeterminant();
    public boolean isInvertible(){
        return !(this.getDeterminant() == 0);
    }
    public boolean isSkewSymmetric(){
        return (this.equals(this.transpose().scalarMultiply(-1)));
    }

    // overriding methods inherited from Object
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getNumberOfRows(); i++) {
            for (int j = 0; j < this.getNumberOfColumns(); j++) {
                sb.append(getElementAt(i, j)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

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
}
