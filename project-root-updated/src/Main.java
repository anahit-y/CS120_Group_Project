import Matrices.GeneralMatrix;
import Matrices.Matrix;
import Vectors.Vector;

public class Main {
    public static void main(String[] args) {
        Vector v = Vector.createVector();
        Vector v1 = Vector.createVector();
        System.out.println(Vector.areLinearlyIndependent(v, v1));
        GeneralMatrix m = new GeneralMatrix();
        System.out.println("Now the second matrix");
        GeneralMatrix m2 = new GeneralMatrix();
        System.out.println(m);
        System.out.println(m.rowToVector(0));
        System.out.println(m2);
        System.out.println(m2.columnToVector(1));
        System.out.println("Result of multiplication");
        System.out.println(GeneralMatrix.multiply(m, m2));
    }
}