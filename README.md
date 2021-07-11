# JavaMatrix

Basic linear algebra operation using Java. 
 
## Compiling and running

`sh project.sh run` compiles and runs the program from the main class.

## Dependencies

Uses `Fraction` class from [this repo](https://github.com/mazadegan/JavaFraction)

<hr>

## Matrix class

#### Constructor
Constructs a `Matrix` instance. Takes an `int` for rows and columns, and an array of type `Fraction` to create a `Matrix` with specific entries. If an array of entries is given and the length does not match the size of the `Matrix` instance, throws an exception.
```java
// construct a 3x4 matrix with all zero entries
Matrix m1 = new Matrix(3, 4);

// construct a 2x2 matrix with specific entries
Fraction[] entries = {new Fraction(1, 2), new Fraction(1, 3), new Fraction(1, 4), new Fraction(1, 5)};
Matrix m2 = new Matrix(2, 2, entries);
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
// set all entries to a random number between 0 and 10
m.randomize(10);

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
Returns a `Matrix` instance that is scaled to the given scalar. Takes a `Fraction` instance.
```java
// construct a 3x3 matrix
Matrix m1 = new Matrix(3, 3);
// randomize entries
m1.randomize(10);
// create scalar fraction
Fraction scalar = new Fraction(5, 1);

Matrix scaledMatrix = m1.scale(scalar); // scales all entries in m1 by 5
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
Returns the transpose of the current `Matrix` instance.

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
Returns a `Fraction` instance that is the determinant of the given `Matrix`. Finds the determinant using Laplace expansion, complexity is O(n!). Throws an exception if the `Matrix` is not square (if rows != columns).

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

Matrix operated = m1.rowOperation(0, 1, mult); // returns m1 after adding 2*row1 to row2
```

#### .fastDeterminant()
Returns a `Fraction` instance that is the determinant of the given `Matrix`. Uses `.makeUpperTriangular()` so complexity is O(n<sup>3</sup>). 

```java
// construct a 3x3 matrix
Matrix m1 = new Matrix(3, 3);
// randomize entries
m1.randomize(10);

Fraction det = m1.fastDeterminant(); // returns the determinant of m1
```

#### .inverse()
Returns a `Matrix` instance that is the inverse of the given `Matrix`. Uses `.fastDeterminant()`, `.minorMatrix()`, `.transpose()`, and `.scale()` as helper functions.

```java
// construct a 3x3 matrix
Matrix m1 = new Matrix(3, 3);
// randomize entries
m1.randomize(10);

Matrix inverse = m1.inverse(); // returns the inverse of m1
```

#### .isEqualTo()
Checks if two `Matrix` instances are equal to each other. Returns a `boolean`.

```java
// construct two 3x3 matrices
Matrix m1 = new Matrix(3, 3);
Matrix m2 = new Matrix(3, 3);
// randomize entries
m1.randomize(10);
m2.randomize(10);

m1.isEqualTo(m2); // <- returns a boolean
```

#### .isOrthogonal()
Checks if a `Matrix` instance is ortogonal. Returns a `boolean`.

```java
// construct a square matrix
Matrix m1 = new Matrix(3, 3);
// randomize entries
m1.randomize(10);

m1.isOrthogonal(); // <- returns a boolean
```
