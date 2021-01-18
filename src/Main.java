public class Main {

    public static void main(String[] args) {
        Matrix m1 = new Matrix(3, 3);
        
        m1.randomize(10);

        m1.showSelf();

        System.out.println("\n-------\n");

        Matrix transpose = m1.transpose();

        transpose.showSelf();
        
    }
}
