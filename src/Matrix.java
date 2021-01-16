import java.util.Arrays;
import java.util.Random;

public class Matrix {
    int rows;
    int columns;
    Fraction[][] data;

    Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new Fraction[rows][columns];

        // Makes zero matrix
        for(int row = 0; row < this.rows; row++) {
            for(int col = 0; col < this.columns; col++) {
                data[row][col] = new Fraction(0, 1);
            }
        }
    }

    // returns a random integer from 0 to range
    private int getRandomNumber(int range) {
        return new Random().nextInt(range);
    }

    // sets all entries in a matrix instance to a random number in
    // in range 0, randRange
    public void randomize(int randRange) {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                int num = getRandomNumber(randRange);
                this.data[row][col] = new Fraction(num, 1);
            }
        }
    }

    public void showSelf() {
        String[][] stringRep = new String[this.rows][this.columns];
        // place string representation of fractions in stringRep
        for(int row = 0; row < this.rows; row++) {
            for(int col = 0; col < this.columns; col++) {
                stringRep[row][col] = this.data[row][col].getString();
            }
        }
        // print rows
        for(int row = 0; row < this.rows; row++) {
            System.out.println(Arrays.toString(stringRep[row]));
        }
    }

    // returns a Matrix instance that is the sum of the two addends
    public Matrix add(Matrix addend) {
        if (this.rows != addend.rows || this.columns != addend.columns) {
            throw new ArithmeticException("Matrices must be same size to perform addition.");
        }
        Matrix sum = new Matrix(this.rows, this.columns);
        for (int row = 0; row < sum.rows; row++) {
            for (int col = 0; col < sum.columns; col++) {
                sum.data[row][col] = this.data[row][col].add(addend.data[row][col]);
            }
        }
        return sum;
    }

    // returns a Matrix instance that is the difference of the two matrices
    public Matrix subtract(Matrix addend) {
        if (this.rows != addend.rows || this.columns != addend.columns) {
            throw new ArithmeticException("Matrices must be same size to perform addition.");
        }
        Matrix difference = new Matrix(this.rows, this.columns);
        for (int row = 0; row < difference.rows; row++) {
            for (int col = 0; col < difference.columns; col++) {
                difference.data[row][col] = this.data[row][col].subtract(addend.data[row][col]);
            }
        }
        return difference;
    }

    // returns a Matrix instance that is scaled to the scalar
    public Matrix scale(int scalar) {
        Matrix scaledMatrix = new Matrix(this.rows, this.columns);
        Fraction scalarFraction = new Fraction(scalar, 1);
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                scaledMatrix.data[row][col] = this.data[row][col].multiply(scalarFraction);
            }
        }
        return scaledMatrix;
    }
}
