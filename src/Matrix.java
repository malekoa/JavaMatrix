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

    // returns the product of two Matrix instances
    public Matrix multiply(Matrix multiplicand) {
        if (this.columns != multiplicand.rows) {
            throw new ArithmeticException("this.columns = " + this.columns + " | multiplicand.rows = " + multiplicand.rows + " - These two must be equal.");
        }
        this.showSelf();
        System.out.println("x");
        multiplicand.showSelf();
        System.out.println("=\n");

        Matrix product = new Matrix(this.rows, multiplicand.columns);
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < multiplicand.columns; col++) {
                // get row data from 1st matrix and column data from second matrix
                Fraction[] rowData = this.data[row];
                Fraction[] colData = multiplicand.getColumn(col);
                Fraction result = new Fraction(0, 1);
                // for each item in the arrays, get the product of each respective entry then sum them.
                for (int entry = 0; entry < rowData.length; entry++) {
                    result = result.add( rowData[entry].multiply(colData[entry]) );
                }
                product.data[row][col] = result;
            }
        }

        return product;
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

    // returns an array of Fractions that corresponds to the entries in the specified column
    public Fraction[] getColumn(int column) { 
        Fraction[] resultArray = new Fraction[this.rows];

        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                if (col == column) {
                    resultArray[row] = this.data[row][col];
                }
            }
        }

        return resultArray;
    }

    // returns a Matrix instance that is the transpose of the given instance
    public Matrix transpose() {
        Matrix transpose = new Matrix(this.rows, this.columns);
        
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                transpose.data[row][col] = this.getColumn(row)[col];
            }
        }

        return transpose;
    }

    // returns a Matrix instance that is the minor matrix of the specified entry
    public Matrix minorMatrix(int entryRow, int entryCol) {
        if(entryRow >= this.rows || entryCol >= this.columns) {
            throw new ArithmeticException("Error: rows or columns out of range. entryRow and entryCol must be smaller than this.rows and this.columns");
        }
        Matrix minor = new Matrix(this.rows - 1, this.columns - 1);
        Fraction[] values = new Fraction[(minor.rows * minor.columns)];

        // fill up values with the entries in the matrix that aren't in the row and column
        int counter = 0;
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                if (row != entryRow && col != entryCol) {
                    values[counter] = this.data[row][col];
                    counter++;
                }
            }
        }

        // place the acquired values into the minor matrix
        counter = 0;
        for (int row = 0; row < minor.rows; row++) {
            for (int col = 0; col < minor.columns; col++) {
                minor.data[row][col] = values[counter];
                counter++;
            }
        }
        return minor;
    }

    // returns a Fraction that is the determinant of the given matrix using cofactor expansion
    public Fraction determinant() {
        if (this.rows != this.columns) {
            throw new ArithmeticException("Rows must equal columns to get determinant");
        }
        Fraction result = new Fraction(0, 1);
        if(this.rows == 2){
            result =  (this.data[0][0].multiply(this.data[1][1])).subtract(this.data[0][1].multiply(this.data[1][0]));
        }
        // implement rule of Sarrus for 3x3 matrix
        if(this.rows >= 3) {
            for (int row = 0; row < this.rows; row++) {
                if (row % 2 == 0) {
                    result = result.add(this.data[row][0].multiply(this.minorMatrix(row, 0).determinant()));
                } else {
                    result = result.subtract(this.data[row][0].multiply(this.minorMatrix(row, 0).determinant()));
                }
            }
        }
        return result;
    }

    // takes operatorRow, multiplies it by the multiplier and adds it do operatingRow
    public void rowOperation(int operatorRowPos, int operatingRowPos, Fraction multiplier) {
        Matrix resultMatrix = new Matrix(this.rows, this.columns);
        // use arrays.copyOf to not pass the reference and not change the original array
        resultMatrix.data = Arrays.copyOf(this.data, this.data.length);
        
        Fraction[] operatorRow = Arrays.copyOf(this.data[operatorRowPos], this.data[operatorRowPos].length);
        Fraction[] operatingRow = Arrays.copyOf(this.data[operatingRowPos], this.data[operatingRowPos].length);

        // multiplies all entries in operatorRow array by the multiplier
        for (int entry = 0; entry < operatorRow.length; entry++) {
            operatorRow[entry] = operatorRow[entry].multiply(multiplier);
        }
        // adds each entry in operatorRow to operatingRow
        for (int entry = 0; entry < operatingRow.length; entry++) {
            operatingRow[entry] = operatingRow[entry].add(operatorRow[entry]);
        }
        // insert operatingRow into resultMatrix at operatingRowPos
        resultMatrix.data[operatingRowPos] = operatingRow;

        resultMatrix.showSelf();
    }

    public void makeTriangular() {
        // make the matrix triangular using only elementary row operations (to avoid dealing with factorization)
    }
}
