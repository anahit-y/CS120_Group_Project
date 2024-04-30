package vectors;

public class Vector implements Cloneable{
    // instance variables
    private double[] vector;

    // constructors
    public Vector(int length){
        this.vector = new double[length];
    }

    public Vector(double[] vector){
        this.vector = vector.clone();
    }

    public Vector(Vector v){
        this(v.vector);
    }

    // accessors
    public double getElementAt(int index){
        return this.vector[index];
    }
    public int getNumberOfElements(){
        return vector.length;
    }
    public int getDimension(){
        return vector.length;
    }

    public double getNorm(){
        return Math.sqrt(dotProduct(this, this));
    }

    public Vector normalize(){
        Vector v = new Vector(vector.length);
        double norm = this.getNorm();
        if (norm != 1 && !this.isZeroVector()){
            for (int i = 0; i < this.vector.length ; i++) {
                v.vector[i] = this.vector[i] / norm;
            }
        }
        return v;
    }

    public boolean isZeroVector(){

        for (int i = 0; i < vector.length; i++){
            if (vector[i] != 0)
                return false;
        }

        return true;
    }

    public static boolean areOrthogonal(Vector v1, Vector v2){
        return dotProduct(v1, v2) == 0;
    }

    public static double dotProduct(Vector v1, Vector v2){
        if (v1.isZeroVector() || v2.isZeroVector()){
            return 0;
        }
        double dotProduct = 0;
        for (int i = 0; i < v1.vector.length; i++){
            dotProduct += v1.vector[i] * v2.vector[i];
        }
        return dotProduct;
    }

    //under review
    public static double cosineOfAngleOfTwoVectors(Vector v1, Vector v2) {
        if (v1.getNorm() == 0 || v2.getNorm() == 0) {
            return 0;
        }
        double dotProduct = Vector.dotProduct(v1, v2);
        double normProduct = v1.getNorm() * v2.getNorm();
        return dotProduct / normProduct;
    }

    public static boolean areCollinear(Vector v1, Vector v2) {
        if (v1.getNorm() == 0 || v2.getNorm() == 0 ) {
            return true;
        }
        return Math.abs(cosineOfAngleOfTwoVectors(v1, v2)) == 1;
    }

    public static Vector add(Vector v1, Vector v2){
        Vector newVector = new Vector(v1.vector.length);
        for (int i = 0; i < v1.vector.length; i++){
            newVector.vector[i] = v1.vector[i] + v2.vector[i];
        }
        return newVector;
    }

    public static Vector subtract(Vector v1, Vector v2){
        Vector newVector = new Vector(v1.vector.length);
        for (int i = 0; i < v1.vector.length; i++){
            newVector.vector[i] = v1.vector[i] - v2.vector[i];
        }
        return newVector;
    }

    public static Vector scalarMultiply(Vector v, double c){
        Vector newVector = new Vector(v.vector.length);
        for (int i = 0; i < v.vector.length; i++){
            newVector.vector[i] = v.vector[i] * c;
        }
        return newVector;
    }

    public static double distanceFrom(Vector v1, Vector v2){
        if (v1.equals(v2))
            return 0;
        double distance = 0;
        for (int i = 0; i < v1.vector.length; i++){
            distance += (v1.vector[i] - v2.vector[i])*(v1.vector[i] - v2.vector[i]);
        }
        return Math.sqrt(distance);
    }

    // defined only for 3-dimensional vectors
    public static Vector crossProduct(Vector v1, Vector v2) {
        double[] newVector = new double[3];
        newVector[0] = v1.vector[1] * v2.vector[2] - v1.vector[2] * v2.vector[1];
        newVector[1] = v1.vector[2] * v2.vector[0] - v1.vector[0] * v2.vector[2];
        newVector[2] = v1.vector[0] * v2.vector[1] - v1.vector[1] * v2.vector[0];
        return new Vector(newVector);
    }

    // overridden methods inherited from Object class
    public boolean equals(Object other){
        if (other == null)
            return false;
        else if (getClass() != other.getClass())
            return false;
        else {
            Vector v = (Vector)other;
            if (this.vector.length != v.vector.length)
                return false;
            for (int i = 0; i < this.vector.length; i++){
                if (this.vector[i] != v.vector[i])
                    return false;
            }
            return true;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double value : this.vector) {
            sb.append(value).append(" ");
        }
        return sb.toString();
    }


    public Object clone(){
        try{
            Vector copy = (Vector)super.clone();
            copy.vector = this.vector.clone();
            return copy;
        }
        catch (CloneNotSupportedException e){
            return null;
        }
    }
}
