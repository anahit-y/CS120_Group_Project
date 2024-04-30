package matrices;

public class SquareMatrix extends GeneralMatrix{

    // constructors
    public SquareMatrix(int size) {
        super(size, size);
    }

    public SquareMatrix(double[][] squareMatrix){
        super(squareMatrix);
    }

    public SquareMatrix(SquareMatrix other){
        super(other);
    }

    // accessors
    public int getSize(){
        return super.getNumberOfColumns();
    }

    public double getTrace(){
        double trace = 0;
        for (int i = 0; i < this.getSize(); i++)
            trace += getElementAt(i, i);
        return trace;
    }

    // helper methods for getDeterminant
    private boolean hasRowOfZeros() {
        for (int i = 0; i < this.getSize(); i++) {
            if (this.rowToVector(i).isZeroVector()){
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
    public double getDeterminant() {
        // base cases
        if (this.getSize() == 1) {
            return this.getElementAt(0, 0);
        }
        else if (this.getSize() == 2) {
            return (this.getElementAt(0, 0) * this.getElementAt(1, 1) - this.getElementAt(0, 1) * this.getElementAt(1, 0));
        }
        // optimization means
        else if (this.hasRowOfZeros() || this.hasIdenticalRows())
            return 0;
        else {
            double determinant = 0;
            for (int i = 0; i < this.getSize(); i++) {
                double[][] minorMatrix = getMinorMatrix(0, i);
                double minorDeterminant = new SquareMatrix(minorMatrix).getDeterminant();
                determinant += this.getElementAt(0, i) * Math.pow(-1, i) * minorDeterminant;
            }
            return determinant;
        }
    }

    private double[][] getMinorMatrix(int rowToRemove, int colToRemove) {
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
        return minorMatrix;
    }



}