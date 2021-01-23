import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class Matrix {

    static final BigInteger ZERO_BIG_INTEGER = BigInteger.valueOf(0);
    static final BigInteger ONE_BIG_INTEGER = BigInteger.valueOf(1);
    static final BigInteger NEGATIVE_ONE_BIG_INTEGER = BigInteger.valueOf(-1);

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
                data[row][col] = new Fraction(ZERO_BIG_INTEGER, ONE_BIG_INTEGER);
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
                BigInteger randBigInteger = new BigInteger(Integer.toString(num));
                this.data[row][col] = new Fraction(randBigInteger, ONE_BIG_INTEGER);
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
                Fraction result = new Fraction(ZERO_BIG_INTEGER, ONE_BIG_INTEGER);
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
    public Matrix scale(BigInteger scalar) {
        Matrix scaledMatrix = new Matrix(this.rows, this.columns);
        Fraction scalarFraction = new Fraction(scalar, ONE_BIG_INTEGER);
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
        Fraction result = new Fraction(ZERO_BIG_INTEGER, ONE_BIG_INTEGER);
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
    public Matrix rowOperation(int operatorRowPos, int operatingRowPos, Fraction multiplier) {
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

        return resultMatrix;
    }

    // returns the value of entry at position
    public Fraction getEntry(int row, int col) {
        return this.data[row][col];
    }

    // find row after rowPosition with nonzero entry at colPosition, returns the position of that row (int)
    // returns -1 if no row with nonzero entry at colPosition is found
    public int findUsableRow(int rowPosition, int colPosition) {

        for (int row = rowPosition + 1; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                if (colPosition == col && !this.data[row][col].getNumerator().equals(ZERO_BIG_INTEGER)) {
                    return row;
                }
            }
        }
        return -1;
    }

    // takes rowPosition and colPosition and, if it finds a useable row below it (a row where the entry at the same column is nonzero...),
    // adds it to row at rowPosition, then returns the matrix
    public Matrix makeUsable(int rowPosition, int colPosition) {
        Matrix usableMatrix = new Matrix(this.rows, this.columns);
        usableMatrix.data = Arrays.copyOf(this.data, this.data.length);
        int usableRowPosition = this.findUsableRow(rowPosition, colPosition);
        if (usableRowPosition >= 0) {
            // takes row at usableRowPosition and adds it to rowPosition
            usableMatrix = this.rowOperation(usableRowPosition, rowPosition, new Fraction(ONE_BIG_INTEGER, ONE_BIG_INTEGER));
        }
        return usableMatrix;
    }

    
    public Matrix subtractDown(int rowPosition, int colPosition) {
        Matrix workingMatrix = new Matrix(this.rows, this.columns);
        workingMatrix.data = Arrays.copyOf(this.data, this.data.length);
        
        for (int rowBelow = rowPosition + 1; rowBelow < workingMatrix.rows; rowBelow++) {
            // if denominator will be 0, return workingMatrix with no changes
            if(workingMatrix.data[rowPosition][colPosition].getNumerator().equals(ZERO_BIG_INTEGER)) {
                return workingMatrix;
            }
            // get right ratio
            Fraction mult = new Fraction(workingMatrix.data[rowBelow][colPosition], workingMatrix.data[rowPosition][colPosition]);
            // multiply it by -1 because we're subtracting
            mult = mult.multiply(new Fraction(NEGATIVE_ONE_BIG_INTEGER, ONE_BIG_INTEGER));
            // perform row operation
            workingMatrix = workingMatrix.rowOperation(rowPosition, rowBelow, mult);
        }

        return workingMatrix;
    }

    public Matrix makeUpperTriangular() {
        Matrix workingMatrix = new Matrix(this.rows, this.columns);
        workingMatrix.data = Arrays.copyOf(this.data, this.data.length);
        // make the matrix triangular using only elementary row operations (to avoid dealing with factorization)
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                if(row == col) {
                    if(workingMatrix.data[row][col].getNumerator().equals(ZERO_BIG_INTEGER)) {
                        workingMatrix = workingMatrix.makeUsable(row, col);
                    }
                    workingMatrix = workingMatrix.subtractDown(row, col);
                }
            }
        }
        return workingMatrix;
    }

    public Fraction fastDeterminant() {
        Matrix workingMatrix = new Matrix(this.rows, this.columns);
        workingMatrix.data = Arrays.copyOf(this.data, this.data.length);
        workingMatrix = workingMatrix.makeUpperTriangular();

        Fraction result = new Fraction(ONE_BIG_INTEGER, ONE_BIG_INTEGER);
        for (int row = 0; row < workingMatrix.rows; row++) {
            for (int col = 0; col < workingMatrix.rows; col++) {
                if (row == col) {
                    result = result.multiply(workingMatrix.data[row][col]);
                }
            }
        }
        System.out.println("fastDeterminant: " + result.getString());
        return result;
    }
}
