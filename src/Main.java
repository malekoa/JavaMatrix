public class Main {

    public static void main(String[] args) {
        Matrix m1 = new Matrix(3, 3);
        Matrix m2 = new Matrix(3, 2);
        m1.randomize(10);
        m2.randomize(10);

          
        m1.multiply(m2);
    }
}
