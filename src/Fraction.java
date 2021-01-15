public class Fraction {
    private long numerator;
    private long denominator;

    // constructor
    Fraction(long numerator, long denominator) {
        if(denominator == 0) {
            throw new ArithmeticException("Fraction constructor received denominator = " + denominator + ". Denominator must be non-zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.reduce();
    }

    public long getDenominator() {
        return denominator;
    }

    public long getNumerator() {
        return numerator;
    }

    // returns a new Fraction instance that is the product of the multiplicand and the multiplier
    public Fraction multiply(Fraction multiplier) {
        long resultNumerator = this.getNumerator() * multiplier.getNumerator();
        long resultDenominator = this.getDenominator() * multiplier.getDenominator();
        return new Fraction(resultNumerator, resultDenominator);
    }

    // returns a new matrix instance that is the product of the dividend and the reciprocal of the divisor (division by multiplication)
    public Fraction divide(Fraction divisor) {
        return multiply(divisor.getReciprocal());
    }

    // returns a new Fraction instance that is the sum of the two addends.
    public Fraction add(Fraction addend) {
        long numerator = addend.getDenominator() * this.getNumerator() + this.getDenominator() * addend.getNumerator();
        long denominator = this.getDenominator() * addend.getDenominator();
        return new Fraction(numerator, denominator);
    }

    // returns a new matrix instance that is the difference between the minuend and the subtrahend
    public Fraction subtract(Fraction subtrahend) {
        long numerator = subtrahend.getDenominator() * this.getNumerator() - this.getDenominator() * subtrahend.getNumerator();
        long denominator = this.getDenominator() * subtrahend.getDenominator();
        return new Fraction(numerator, denominator);
    }

    // returns a new Fraction instance that is the reciprocal of the fraction, i.e. 25/2 -> 2/25
    public Fraction getReciprocal() {
        return new Fraction(this.getDenominator(), this.getNumerator());
    }

    // returns the fraction in string form and returns only the numerator if the denominator == 1
    public String getString() {
        if(this.getDenominator() == 1) {
            return Long.toString(this.getNumerator());
        }
        String num = Long.toString(this.getNumerator());
        String den = Long.toString(this.getDenominator());
        return num.concat("/").concat(den);
    }

    // reduces fraction to lowest terms, i.e. 50/4 -> 25/2
    public void reduce() {
        long gcd = this.getGCD(this.getNumerator(), this.getDenominator());
        this.numerator = (this.getNumerator() / gcd);
        this.denominator = (this.getDenominator() / gcd);
        if(this.getDenominator() < 0) {
            this.numerator *= -1;
            this.denominator *= -1;
        }
    }

    // returns the greatest common denominator of 'a' and 'b' using Euclid's algorithm
    public long getGCD(long a, long b) {
        long r = a % b;
        if(r != 0) {
            return this.getGCD(b, r);
        }
        return b;
    }
}
