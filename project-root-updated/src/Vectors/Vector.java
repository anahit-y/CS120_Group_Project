package Vectors;
import java.util.Scanner;
import Exceptions.InvalidSizeException;

public class Vector implements Cloneable{
    private double[] vector;

    public Vector(int length){
        this.vector = new double[length];
    }

    public Vector(double[] vector){
        this.vector = new double[vector.length];
        for (int i = 0; i < vector.length; i++){
            this.vector[i] = vector[i];
        }
    }

    public double[] getVector(){
        return this.vector;
    }

    public double getNorm(){
        double norm = 0;
        for (double i : this.vector){
            norm += i*i;
        }
        return norm;
    }

    public boolean isZeroVector(){
        return this.getNorm() == 0;
    }

    public void normalize(){
        double norm = this.getNorm();
        if (norm != 1){
            for (int i = 0; i < this.vector.length ; i++) {
                this.vector[i] /= Math.sqrt(norm);
            }
        }
    }

    //throws InvalidSizeException

    public static double dotProduct(Vector a, Vector b) {
        if (a.isZeroVector() || b.isZeroVector()){
            return 0;
        }
        if (a.vector.length != b.vector.length){
            System.out.println("Vectors must have the same length for dot product calculation");
            return 0;
        }

        double dotProduct = 0;
        for (int i = 0; i < a.vector.length; i++){
            dotProduct += a.vector[i] * b.vector[i];
        }
        return dotProduct;
    }

    public static Vector crossProduct(Vector a, Vector b) throws InvalidSizeException {
        int n = a.vector.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                int k = (i + j + 1) % n;
                int l = (i + j + n) % n;
                sum += a.vector[k] * b.vector[l];
            }
            result[i] = sum;
        }
        return new Vector(result);
    }


    public static Vector createVector(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the number of elements of your vector");
        int length = sc.nextInt();
//        if (length < 0){
//            throw new InvalidSizeException();
//        }
        Vector v = new Vector(length);
        for (int i = 0; i<length; i++){
            int n = sc.nextInt();
            v.vector[i] = n;
        }
        return v;
    }

    public String toString(){
        String s = "";
        for (double i : this.vector){
            s += (i + " ");
        }
        return s;
    }

    public static boolean areLinearlyIndependent(Vector a, Vector b){
        double scalarMultiple = a.vector[0]/(double)b.vector[0];
        int n = 1;
        while (n < a.vector.length){
            if (scalarMultiple != a.vector[n]/(double)b.vector[n])
                return false;
            n++;
        }
        return true;
    }

}
