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

    // returns a random integer from 0 to range
    private int getRandomNumber(int range) {
        return new Random().nextInt(range);
    }
}
