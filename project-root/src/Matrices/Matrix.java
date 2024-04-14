package Matrices;
import Vectors.Vector;
import java.util.Scanner;

public class Matrix {
    private double[][] matrix;

    public Matrix(){
        this.matrix = Matrix.createMatrix();
    }

    public static double[][] createMatrix(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the number of elements of your vector");
        int numberOfRows = sc.nextInt();
        int numberOfColumns = sc.nextInt();
        Matrix m = new Matrix(numberOfRows, numberOfColumns);
        for (int i = 0; i < numberOfRows; i++){
            int n = 0;
            for (int j = 0; j < numberOfColumns; j++){
                m.matrix[i][j] = sc.nextInt();
            }
            n += numberOfRows;
        }
        return m.matrix;
    }

    public Matrix(int numberOfRows, int numberOfColumns){
        this.matrix = new double[numberOfRows][numberOfColumns];
    }


    public String toString(){
        String s = "";
        for (int i = 0; i < this.matrix.length; i++){
            for (int j = 0; j< this.matrix[i].length; j++){
                s += this.matrix[i][j] + " ";
            }
            s += " \n";
        }
        return s;
    }

    public static Matrix add(Matrix m1, Matrix m2){
        Matrix result = new Matrix(m1.matrix.length, m1.matrix[0].length);
        for (int i = 0; i < m1.matrix.length; i++){
            for (int j = 0; j< m1.matrix[i].length; j++){
                result.matrix[i][j] = m1.matrix[i][j] + m2.matrix[i][j];
            }
        }
        return result;
    }

    public static Matrix scalarMultiply(Matrix m1, double c){
        Matrix result = new Matrix(m1.matrix.length, m1.matrix[0].length);
        for (int i = 0; i < m1.matrix.length; i++){
            for (int j = 0; j< m1.matrix[i].length; j++){
                result.matrix[i][j] = m1.matrix[i][j] * c;
            }
        }
        return result;
    }
    public static Matrix subtract(Matrix m1, Matrix m2){
        return Matrix.add(m1, Matrix.scalarMultiply(m2, -1));
    }

//    public static Matrix multiply(Matrix m1, Matrix m2){
//        Matrix result = new Matrix(m1.matrix.length, m2.matrix[0].length);
//
//    }
    

}
