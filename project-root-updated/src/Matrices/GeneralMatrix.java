package Matrices;
import Exceptions.InvalidSizeException;
import Vectors.Vector;
import java.util.Scanner;



public class GeneralMatrix implements Matrix {
    private double[][] matrix;
    private int numberOfRows;
    private int numberOfColumns;

    public GeneralMatrix(){
        this.matrix = GeneralMatrix.createMatrix();
    }

    public static double[][] createMatrix(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the number of rows in your matrix");
        int numberOfRows = sc.nextInt();
        System.out.println("Input the number of columns in your matrix");
        int numberOfColumns = sc.nextInt();
        GeneralMatrix m = new GeneralMatrix(numberOfRows, numberOfColumns);
        System.out.println("Input the elements of your matrix");
        for (int i = 0; i < numberOfRows; i++){
            int n = 0;
            for (int j = 0; j < numberOfColumns; j++){
                m.matrix[i][j] = sc.nextInt();
            }
            n += numberOfRows;
        }
        return m.matrix;
    }

    public GeneralMatrix(int numberOfRows, int numberOfColumns){
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.matrix = new double[numberOfRows][numberOfColumns];
    }
    static GeneralMatrix generateRandomMatrix(int rows, int cols){
        GeneralMatrix randomMatrix = new GeneralMatrix(rows,cols);
        for (int i = 0; i<rows; i++){
            for(int j = 0; j< cols; j++){
                randomMatrix.matrix[i][j] = Math.random();
            }
        }
        return randomMatrix;
    }
//    public boolean isInvertible(){
//
//    }
    public boolean isEqual(GeneralMatrix other) {
        boolean checker = true;
        if (other.numberOfRows == this.numberOfRows && other.numberOfColumns == this.numberOfColumns) {
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    if (matrix[i][j]!=other.matrix[i][j])
                        checker = false;
                }
            }
            return checker;
        }
        else return false;
    }
    public GeneralMatrix(double[][] array){
        GeneralMatrix m = new GeneralMatrix(array.length, array[0].length);
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                m.matrix[i][j] = array[i][j];
            }
        }
    }

    public GeneralMatrix(GeneralMatrix other){
        this.numberOfColumns = other.numberOfColumns;
        this.numberOfRows = other.numberOfRows;
        new GeneralMatrix(other.matrix);
    }

    public String toString(){
        String s = "";
        for (int i = 0; i < this.numberOfRows; i++){
            for (int j = 0; j < this.numberOfColumns; j++){
                s += this.matrix[i][j] + " ";
            }
            s += " \n";
        }
        return s;
    }

// throws Exception
    public static GeneralMatrix add(GeneralMatrix m1, GeneralMatrix m2){

        GeneralMatrix result = new GeneralMatrix(m1.numberOfRows, m1.numberOfColumns);
        for (int i = 0; i < m1.numberOfRows; i++){
            for (int j = 0; j< m1.numberOfColumns; j++){
                result.matrix[i][j] = m1.matrix[i][j] + m2.matrix[i][j];
            }
        }
        return result;
    }
    public GeneralMatrix scalarMultiply(double c){

        for (int i = 0; i < this.numberOfRows; i++){
            for (int j = 0; j< this.numberOfColumns; j++){
                matrix[i][j] *= c;
            }
        }
        GeneralMatrix result =  new GeneralMatrix(matrix);
        return result;
    }

    public static GeneralMatrix scalarMultiply(GeneralMatrix m1, double c){
        GeneralMatrix result = new GeneralMatrix(m1.numberOfRows, m1.numberOfColumns);
        for (int i = 0; i < m1.numberOfRows; i++){
            for (int j = 0; j< m1.numberOfColumns; j++){
                result.matrix[i][j] = m1.matrix[i][j] * c;
            }
        }
        return result;
    }
    public static GeneralMatrix subtract(GeneralMatrix m1, GeneralMatrix m2){
        return GeneralMatrix.add(m1, GeneralMatrix.scalarMultiply(m2, -1));
    }

//    public static Matrix multiply(Matrix m1, Matrix m2) {
////        if (m1.getNumberOfColumns() != m2.getNumberOfRows()) {
////            System.out.println("Matrices cannot be multiplied");
////            return null;
////        }
//
//        Matrix result = new Matrix(m1.getNumberOfRows(), m2.getNumberOfColumns());
//        for (int i = 0; i < m1.getNumberOfRows(); i++) {
//            for (int j = 0; j < m2.getNumberOfColumns(); j++) {
//                Vector rowVector = m1.rowToVector(i);
//                Vector columnVector = m2.columnToVector(j);
//                double dotProduct = Vector.dotProduct(rowVector, columnVector);
//                result.matrix[i][j] = dotProduct;
//            }
//        }
//        return result;
//    }


    // order matters, throws InvalidSizeException
    // works well
    public static GeneralMatrix multiply(GeneralMatrix m1, GeneralMatrix m2) {
        if (m1.getNumberOfColumns() != m2.getNumberOfRows()) {
            System.out.println("Matrices cannot be multiplied");
            return null;
        }

        GeneralMatrix result = new GeneralMatrix(m1.getNumberOfRows(), m2.getNumberOfColumns());
        for (int i = 0; i < m1.getNumberOfRows(); i++) {
            for (int j = 0; j < m2.getNumberOfColumns(); j++) {
                double dotProduct = 0;
                for (int k = 0; k < m1.getNumberOfColumns(); k++) {
                    dotProduct += m1.matrix[i][k] * m2.matrix[k][j];
                }
                result.matrix[i][j] = dotProduct;
            }
        }
        return result;
    }


    public boolean isSquareMatrix(){
        return this.getNumberOfColumns() == this.getNumberOfRows();

    }
    public static GeneralMatrix transpose(GeneralMatrix m) throws InvalidSizeException{
        if (!m.isSquareMatrix())
            throw new InvalidSizeException();
        // even though they are equal
        GeneralMatrix transposed = new GeneralMatrix(m.getNumberOfRows(), m.getNumberOfColumns());
        for (int i = 0; i < m.getNumberOfRows(); i++){
            for (int j =0; j < m.getNumberOfColumns(); j++){
                transposed.matrix[i][j] = m.matrix[j][i];
            }
        }
        return transposed;
    }

    public int getNumberOfColumns(){
        return this.numberOfColumns;
    }

    public int getNumberOfRows(){
        return this.numberOfRows;
    }
    public Vector rowToVector(int numberOfRow){
        Vector v = new Vector(this.matrix[numberOfRow]);
        return v;
    }

    public Vector columnToVector(int numberOfColumn){
        double[] v = new double[this.getNumberOfColumns()];
        for (int i = 0; i < v.length; i++){
            v[i] = this.matrix[i][numberOfColumn];
        }
        return new Vector(v);
    }

}
