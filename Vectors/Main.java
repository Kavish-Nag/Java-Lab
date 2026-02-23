public class Main {

    public static void main(String[] args) {

        try {

            Vector v1 = new Vector(new double[]{1, 2, 3});
            Vector v2 = new Vector(new double[]{4, 5});

            System.out.println("Vector 1:");
            v1.printVector(v1);

            System.out.println("Vector 2:");
            v2.printVector(v2);

            Vector sum = v1.add(v2);
            System.out.println("Addition:");
            sum.printVector(sum);

            Vector diff = v1.subtract(v2);
            System.out.println("Subtraction:");
            diff.printVector(diff);

            double dot = v1.dotProduct(v2);
            System.out.println("Dot Product: " + dot);

        } 
        catch (VectorException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}