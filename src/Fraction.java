import java.math.BigInteger;

public class Fraction {
    static final BigInteger ZERO_BIG_INTEGER = BigInteger.valueOf(0);
    static final BigInteger ONE_BIG_INTEGER = BigInteger.valueOf(1);
    static final BigInteger NEGATIVE_ONE_BIG_INTEGER = BigInteger.valueOf(-1);
    static final String DENOMINATOR_NONZERO_WARNING = "Denominator must be nonzero";

    private BigInteger numerator;
    private BigInteger denominator;

    // constructor that takes two big integers
    Fraction(BigInteger numerator, BigInteger denominator) {
        if(denominator.equals(ZERO_BIG_INTEGER)) {
            throw new ArithmeticException(DENOMINATOR_NONZERO_WARNING);
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.reduce();
    }

    // constructor that takes two integers
    Fraction(int numerator, int denominator) {
        if(denominator == 0) {
            throw new ArithmeticException(DENOMINATOR_NONZERO_WARNING);
        }
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.valueOf(denominator);
        this.reduce();
    }

    // constructor that takes two fractions
    Fraction(Fraction numerator, Fraction denominator) {
        if(denominator.getNumerator().equals(ZERO_BIG_INTEGER)) {
            throw new ArithmeticException(DENOMINATOR_NONZERO_WARNING);
        }
        Fraction quotient = numerator.divide(denominator);
        this.numerator = quotient.numerator;
        this.denominator = quotient.denominator;
        this.reduce();
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    // returns a new Fraction instance that is the product of the multiplicand and the multiplier
    public Fraction multiply(Fraction multiplier) {
        BigInteger resultNumerator = this.getNumerator().multiply(multiplier.getNumerator());
        BigInteger resultDenominator = this.getDenominator().multiply(multiplier.getDenominator());
        return new Fraction(resultNumerator, resultDenominator);
    }

    // returns a new matrix instance that is the product of the dividend and the reciprocal of the divisor (division by multiplication)
    public Fraction divide(Fraction divisor) {
        return multiply(divisor.getReciprocal());
    }

    // returns a new Fraction instance that is the sum of the two addends.
    public Fraction add(Fraction addend) {
        BigInteger numerator = addend.getDenominator().multiply(this.getNumerator()).add(this.getDenominator().multiply(addend.getNumerator()));
        BigInteger denominator = this.getDenominator().multiply(addend.getDenominator());
        return new Fraction(numerator, denominator);
    }

    // returns a new matrix instance that is the difference between the minuend and the subtrahend
    public Fraction subtract(Fraction subtrahend) {
        BigInteger numerator = subtrahend.getDenominator().multiply(this.getNumerator()).subtract(this.getDenominator().multiply(subtrahend.getNumerator()));
        BigInteger denominator = this.getDenominator().multiply(subtrahend.getDenominator());
        return new Fraction(numerator, denominator);
    }

    // returns a new Fraction instance that is the reciprocal of the fraction, i.e. 25/2 -> 2/25
    public Fraction getReciprocal() {
        return new Fraction(this.getDenominator(), this.getNumerator());
    }

    // returns the fraction in string form and returns only the numerator if the denominator == 1
    public String getString() {
        if(this.getDenominator().equals(ONE_BIG_INTEGER)) {
            return this.getNumerator().toString();
        }
        String num = this.getNumerator().toString();
        String den = this.getDenominator().toString();
        return num.concat("/").concat(den);
    }

    // reduces fraction to lowest terms, i.e. 50/4 -> 25/2
    private void reduce() {
        BigInteger gcd = this.getGCD(this.getNumerator(), this.getDenominator());
        this.numerator = (this.getNumerator().divide(gcd));
        this.denominator = (this.getDenominator().divide(gcd));
        if(this.getDenominator().compareTo(ZERO_BIG_INTEGER) < 0) {
            this.numerator = this.numerator.multiply(NEGATIVE_ONE_BIG_INTEGER);
            this.denominator = this.denominator.multiply(NEGATIVE_ONE_BIG_INTEGER);
        }
    }

    // returns the greatest common denominator of 'a' and 'b' using Euclid's algorithm
    public BigInteger getGCD(BigInteger a, BigInteger b) {
        BigInteger r = a.remainder(b);
        if(!r.equals(ZERO_BIG_INTEGER)) {
            return this.getGCD(b, r);
        }
        return b;
    }

    // returns true if both fractions are equal, false otherwise
    public boolean isEqualTo(Fraction otherFraction) {
        return (this.numerator.equals(otherFraction.numerator) && this.denominator.equals(otherFraction.denominator));

    }
}
