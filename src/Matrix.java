package matrices;
import vectors.Vector;

public interface Matrix{
    public Vector rowToVector(int rowNumber);

    public Vector columnToVector(int columnNumber);

    public static GeneralMatrix add(GeneralMatrix m1, GeneralMatrix m2) {
        return null;
    }

    public static GeneralMatrix subtract(GeneralMatrix m1, GeneralMatrix m2) {
        return null;
    }

    public static GeneralMatrix multiply(GeneralMatrix m1, GeneralMatrix m2) {
        return null;
    }

    public static GeneralMatrix scalarMultiply(GeneralMatrix m, double c) {
        return null;
    }

    public static GeneralMatrix transpose(GeneralMatrix m) {
        return null;
    }
}
