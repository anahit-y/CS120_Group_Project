package Matrices;

public abstract class DiagonalMatrix implements Matrix {
    private double[] matrix;

    public double determinant(){
        double product = 1;
        for (double d : this.matrix){
            product *= d;
        }
        return product;
    }

}
