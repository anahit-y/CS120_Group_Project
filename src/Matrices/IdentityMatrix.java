package Matrices;

import Matrices.AbstractSquareMatrix;

public class IdentityMatrix extends AbstractSquareMatrix {
    private int size; //number of Rows and Columns

    public IdentityMatrix(int size){
        this.size = size;
    }

    public IdentityMatrix(){
        this(3);
    }

    public int determinant(){
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


}
