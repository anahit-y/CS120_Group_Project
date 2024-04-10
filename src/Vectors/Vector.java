package Vectors;
import java.util.Scanner;
import Exceptions.InvalidSizeException;

public class Vector {
    private double[] vector;

    public Vector(int length){
        this.vector = new double[length];
    }

    public Vector(int[] vector){
        this.vector = new double[vector.length];
        for (int i = 0; i < vector.length; i++){
            this.vector[i] = vector[i];
        }
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
        double dotProduct = 0;
        for (int i = 0; i < a.vector.length; i++){
            dotProduct += a.vector[i]*b.vector[i];
        }
        return dotProduct;
    }

//    public static double[] crossProduct(Vector a, Vector b){
//        Vector v = new Vector(a.vector.length);
//        for (int i = a.vector.length-1, j = 0; i >= 0; i++, j++){
//            v.vector[j] = a.vector[i-1]*b.vector[i] - b.vector[i-1]*a.vector[i];
//
//        }
//    }

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
