package vectors;

public class VectorDemo {
    public static void main(String[] args) {
        // Create some vectors for testing
        Vector v1 = new Vector(new double[]{1, 2, 3});
        Vector v2 = new Vector(new double[]{4, 5, 6});
        Vector zeroVector = new Vector(3); // Zero vector
        Vector collinearVector = new Vector(new double[]{2, 4, 6}); // Collinear with v1

        // Test getNorm method
        System.out.println("Norm of v1: " + v1.getNorm()); // Expected output: sqrt(1^2 + 2^2 + 3^2) = sqrt(14)

        // Test normalize method
        Vector normalizedV1 = v1.normalize();
        System.out.println("Normalized v1: " + normalizedV1); // Expected output: v1 / norm(v1)

        // Test areOrthogonal method
        System.out.println("Are v1 and v2 orthogonal? " + Vector.areOrthogonal(v1, v2)); // Expected output: false

        // Test cosineOfAngleOfTwoVectors method
        System.out.println("Cosine of angle between v1 and v2: " + Vector.cosineOfAngleOfTwoVectors(v1, v2));

        // Test areCollinear method
        System.out.println("Are v1 and collinearVector collinear? " + Vector.areCollinear(v1, collinearVector)); // Expected output: true

        // Test crossProduct method
        Vector crossProduct = Vector.crossProduct(v1, v2);
        System.out.println("Cross product of v1 and v2: " + crossProduct); // Expected output: (-3, 6, -3)
    }
}

