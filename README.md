# JavaMatrix

For learning java!

## Compiling and running

`sh java.sh run` compiles and runs the program from the main class.

### Fraction class

#### Constructor
Constructs a `Fraction` instance. Takes a `long` numerator and a `long` denominator.
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

### Matrix class

#### Constructor
Constructs a `Matrix` instance. Takes an `int` rows and an `int` columns.
```java
// construct a 3x4 matrix
Matrix m = new Matrix(3, 4);
```

#### .randomize()
Sets all entries in the `Matrix` instance to a random integer between 0 and `randRange`
```java
// construct a 3x3 matrix
Matrix m = new Matrix(3, 3);
// set all entries to a random number between 0 and 20
m.randomize(20);
```

#### .showSelf()
Prints the `Matrix` instance to the console. 
```java
// construct a 3x3 matrix
Matrix m = new Matrix(3, 3);
// set all entries to a random number between 0 and 20
m.randomize(20);
```