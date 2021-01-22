public class Main {

    public static void main(String[] args) {
        final int SIZE = 25;
        Matrix m1 = new Matrix(SIZE, SIZE);
        
        m1.randomize(10);

        m1.showSelf();

        System.out.println("\n-----\n");

        long startTime = System.nanoTime();
        m1.fastDeterminant();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println(".fastDeterminant() time (ms): " + duration);
        // startTime = System.nanoTime();
        // Fraction det = m1.determinant();
        // endTime = System.nanoTime();
        // duration = (endTime - startTime)/1000000;
        // System.out.println("\ndeterminant: " + det.getString());
        
        // System.out.println(".determinant() time (ms): " + duration);
        

    }
}
