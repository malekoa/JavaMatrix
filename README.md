# JavaMatrix

For learning java!

## Compiling and running

`sh project.sh run` compiles and runs the program from the main class.

## To do

- [x] ~~Use `BigInteger` instead of `long` for `numerator` and `denominator` in `Fraction`~~
- [ ] Make `Fraction` constructor that takes a `BigDecimal` and converts the `Fraction` to `BigInteger`.
- [ ] `.findUseableRow()` can be improved. There is no need for the second for loop, all searches are being done on the same column.
- [ ] Method for inverting `Matrix` instance

## Fraction class

#### Constructor
Constructs a `Fraction` instance. Takes a `BigInteger` numerator and a `BigInteger` denominator.
```java
// construct a new fraction -> "1/2"
Fraction f = new Fraction(1, 2);
```

#### .multiply()
Returns a new `Fraction` instance that is the product of the multiplicand and the multiplier.
```java
Fraction f1 = new Fraction(1, 2); // -> 1/2
Fraction f2 = new Fraction(1, 5); // -> 1/5
Fraction product = f1.multiply(f2); // returns -> 1/10
```

#### .divide()
Returns a new `Fraction` instance that is the quotient of the dividend and the divisor.
```java
Fraction f1 = new Fraction(1, 2); // -> 1/2
Fraction f2 = new Fraction(1, 5); // -> 1/5
Fraction quotient = f1.divide(f2); // returns -> 5/2
```

#### .add()
Returns a new `Fraction` instance that is the sum of the two addends.
```java
Fraction f1 = new Fraction(1, 2); // -> 1/2
Fraction f2 = new Fraction(1, 5); // -> 1/5
Fraction sum = f1.add(f2); // returns -> 7/10
```

#### .subtract()
Returns a new `Fraction` instance that is the difference between the minuend and the subtrahend.
```java
Fraction f1 = new Fraction(1, 2); // -> 1/2
Fraction f2 = new Fraction(1, 5); // -> 1/5
Fraction difference = f1.subtract(f2); // returns -> 3/10
```

#### .getReciprocal()
Returns a new `Fraction` instance that is the reciprocal of the fraction.
```java
Fraction f1 = new Fraction(1, 2); // -> 2/3
Fraction reciprocal = f1.getReciprocal(f1); // returns -> 3/2
```

#### .getString()
Returns the fraction in `string` form.  If the denominator = 1, returns only the numerator.
```java
Fraction f1 = new Fraction(1, 2); // -> 1/2
Fraction s1 = f1.getString(); // returns -> "1/2"

Fraction f2 = new Fraction(5, 1); // -> 5/1
Fraction s2 = f2.getString(); // returns -> "5"
```

#### .getGCD()
Returns a `long` that is the greatest common denominator of 'a' and 'b' using Euclid's algorithm. Used by `.reduce()` to get the greatest common denominator between the numerator and denominator.
```java
Fraction f = new Fraction(1, 2);
long gcd = f.getGCD(10, 20); // -> returns 10
```

#### .reduce()
Reduces fraction to lowest terms. Used by the `Fraction` constructor to reduce reducible fractions.

<hr>

## Matrix class

#### Constructor
Constructs a `Matrix` instance. Takes an `int` for rows and an `int` for columns.
```java
// construct a 3x4 matrix
Matrix m = new Matrix(3, 4);
```

#### .randomize()
Sets all entries in the `Matrix` instance to a random integer between 0 and the `int` that it takes.
```java
// construct a 3x3 matrix
Matrix m = new Matrix(3, 3);

m.randomize(10); // set all entries to a random number between 0 and 10
```

#### .showSelf()
Prints the `Matrix` instance to the console. 
```java
// construct a 3x3 matrix
Matrix m = new Matrix(3, 3);
// set all entries to a random number between 0 and 20
m.randomize(20);

m.showSelf(); // prints 'm' to console
```

#### .add()
Returns a `Matrix` instance that is the sum of the two `Matrix` instances. Throws an exception if the arrays have different sizes. Takes another `Matrix` instance.
```java
// construct two 3x3 matrices
Matrix m1 = new Matrix(3, 3);
Matrix m2 = new Matrix(3, 3);
// randomize entries in all matrices
m1.randomize(10);
m2.randomize(10);

Matrix sum = m1.add(m2); // returns the result of m1 + m2
```

#### .subtract()
Returns a `Matrix` instance that is the difference of the two `Matrix` instances. Throws an exception if the arrays have different sizes. Takes another `Matrix` instance.
```java
// construct two 3x3 matrices
Matrix m1 = new Matrix(3, 3);
Matrix m2 = new Matrix(3, 3);
// randomize entries in all matrices
m1.randomize(10);
m2.randomize(10);

Matrix difference = m1.subtract(m2); // returns the result of m1 - m2
```

#### .multiply()
Returns a `Matrix` instance that is the product of the two `Matrix` instances. Throws an exception if the arrays have incompatible sizes. Takes another `Matrix` instance.
```java
// construct two 3x3 matrices
Matrix m1 = new Matrix(3, 3);
Matrix m2 = new Matrix(3, 3);
// randomize entries in all matrices
m1.randomize(10);
m2.randomize(10);

Matrix product = m1.multiply(m2); // returns the result of m1 * m2
```

#### .scale()
Returns a `Matrix` instance that is scaled to the given scalar. Takes an `int`.
```java
// construct a 3x3 matrix
Matrix m1 = new Matrix(3, 3);
// randomize entries
m1.randomize(10);

Matrix scaledMatrix = m1.scale(5); // scales all entries in m1 by 5
```

#### .getColumn()
Returns an array of type `Fraction` that corresponds to the entries in the specified column. Takes an `int`.
```java
// construct a 3x3 matrix
Matrix m1 = new Matrix(3, 3);
// randomize entries
m1.randomize(10);

Matrix getColumn = m1.getColumn(0); // returns the 0th column of matrix m1
```

#### .transpose()
Returns a `Matrix` instance that is the transpose of the given `Matrix`. Takes a `Matrix` instance.

```java
// construct a 3x3 matrix
Matrix m1 = new Matrix(3, 3);
// randomize entries
m1.randomize(10);

Matrix mTranspose = m1.transpose(); // returns the transpose of Matrix 'm1'
```

#### .minorMatrix()
Returns a `Matrix` instance that is the minor of the matrix in relation to the given entry position. Takes an `int` for entryRow and an `int` for entryColumn.

```java
// construct a 3x3 matrix
Matrix m1 = new Matrix(3, 3);
// randomize entries
m1.randomize(10);

Matrix minor = m1.minor(0, 0); // returns the minor of Matrix 'm1' in relation to the entry in the 1st column and 1st row
```

#### .determinant()
Returns a `Fraction` instance that is the determinant of the given `Matrix`. Finds the determinant using Laplace expansion = very slow. If you want to get the result faster, use `.fastDeterminant()`. Throws an exception if the `Matrix` is not square (if rows != columns).

```java
// construct a 3x3 matrix
Matrix m1 = new Matrix(3, 3);
// randomize entries
m1.randomize(10);

Fraction det = m1.determinant(); // returns the determinant of m1
```

#### .rowOperation()
Returns a `Matrix` instance that is the result of an elementary row operation. Takes row at position `int` operatorRow, multiplies it by `Fraction` multiplier and adds it to row at position `int` operatingRow.

```java
// construct a 3x3 matrix
Matrix m1 = new Matrix(3, 3);
// randomize entries
m1.randomize(10);
// make multiplier Fraction
Fraction mult = new Fraction(2, 1);

Fraction operated = m1.rowOperation(0, 1, mult); // returns m1 after adding 2*row1 to row2
```

#### .fastDeterminant()
Returns a `Fraction` instance that is the determinant of the given `Matrix`. Uses `.makeUpperTriangular()` and so is much faster than `.determinant()`. 