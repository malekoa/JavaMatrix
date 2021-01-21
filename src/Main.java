public class Main {

    public static void main(String[] args) {
        Matrix m1 = new Matrix(3, 3);
        
        m1.randomize(10);

        m1.showSelf();

        System.out.println("\n-----\n");

        Fraction mult = new Fraction(-1, 1);

        m1.rowOperation(0, 1, mult);

        System.out.println("\n-----\n");

        m1.showSelf();
    }
}
