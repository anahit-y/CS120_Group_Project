package matrices;
public class Main {
    public static void main(String[] args) {
        IdentityMatrix s1 = new IdentityMatrix(3);
        SquareMatrix s = new SquareMatrix(new double[][]{{1,2,3}, {5,8,9}, {10, 10, 11}});
        Matrix n = (s.inverse());
        System.out.println(n);
        System.out.println(s.getDeterminant());
        System.out.println(s.inverse().getDeterminant() * s.getDeterminant());
    }
}

