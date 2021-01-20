public class Main {

    public static void main(String[] args) {
        Matrix m1 = new Matrix(3, 3);
        
        m1.randomize(10);

        m1.showSelf();

        System.out.println("\n-----\n");
        
        Fraction det = m1.determinant();

        System.out.println("Determinant: " + det.getString());
    }
}
