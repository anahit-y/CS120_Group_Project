package Matrices;

public class SquareMatrix implements Matrix {
    private int size;
    private double[][] matrix;

    public int getNumberOfRows(){
        return size;
    }
    public int getNumberOfColumns(){
        return size;
    }
    public SquareMatrix(int size, double[][] matrix) {
        this.size = size;
        this.matrix = matrix;
    }
    public SquareMatrix scalarMultiply(double c){

        for (int i = 0; i < this.size; i++){
            for (int j = 0; j< this.size; j++){
                this.matrix[i][j] *= c;
            }
        }
        SquareMatrix result =  new SquareMatrix(size, matrix);
        return result;
    }

    public double determinant() {
        return determinant(this.matrix, this.size);
    }

    private double determinant(double[][] matrix, int n) {
        double det = 0;

        if (n == 1) {
            return matrix[0][0];
        } else if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else
            return 0.5; //just wrote random number, should add the part when n>2, with cofactor
    }
}

