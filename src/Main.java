package matrices;
public class Main {
    public static void main(String[] args) {
        IdentityMatrix s1 = new IdentityMatrix(3);
        SymmetricMatrix s = new SymmetricMatrix(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        GeneralMatrix n = new GeneralMatrix(new double[][]{{1, 0}, {3,5}, {7, 9}, {0,3}});
        System.out.println(s.multiply(n));
//        System.out.println(s.getDeterminant());
//        System.out.println(s.inverse().getDeterminant() * s.getDeterminant());
    }
}

