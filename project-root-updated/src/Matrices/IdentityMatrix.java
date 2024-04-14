package Matrices;

public abstract class IdentityMatrix extends DiagonalMatrix {
    private int size; //number of Rows and Columns

    public IdentityMatrix(int size){
        this.size = size;
    }

    public IdentityMatrix(){
        this(3);
    }

    public double determinant(){
        return 1;
    }

    public int getRank(){
        return size;
    }

    public int getSize(){
        return size;
    }

    public boolean isInvertible(){
        return true;
    }

    public static boolean isIdentity(Matrix m){
        return true;
    }


}
