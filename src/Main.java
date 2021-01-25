public class Main {

    public static void main(String[] args) {
        final int SIZE = 3;
        Fraction[] entries = {new Fraction(3, 5), new Fraction(12, 25), new Fraction(16, 25), new Fraction(-4, 5), new Fraction(9, 25), new Fraction(12, 25), new Fraction(0, 1), new Fraction(-4, 5), new Fraction(3, 5)};
        Matrix m1 = new Matrix(SIZE, SIZE, entries);
        m1.showSelf();
        
        
    }
}
