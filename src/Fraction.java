import java.math.BigInteger;

public class Fraction {
    private BigInteger numerator;
    private BigInteger denominator;

    // constructor
    Fraction(BigInteger numerator, BigInteger denominator) {
        BigInteger zero = new BigInteger("0");
        if(denominator.equals(zero)) {
            throw new ArithmeticException("Fraction constructor received denominator = " + denominator + ". Denominator must be non-zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.reduce();
    }

    Fraction(Fraction numerator, Fraction denominator) {
        BigInteger zero = new BigInteger("0");
        if(denominator.getNumerator().equals(zero)) {
            throw new ArithmeticException("Fraction constructor received denominator = " + denominator + ". Denominator must be non-zero");
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
        BigInteger one = new BigInteger("1");
        if(this.getDenominator().equals(one)) {
            return this.getNumerator().toString();
        }
        String num = this.getNumerator().toString();
        String den = this.getDenominator().toString();
        return num.concat("/").concat(den);
    }

    // reduces fraction to lowest terms, i.e. 50/4 -> 25/2
    private void reduce() {
        BigInteger zero = new BigInteger("0");
        BigInteger negOne = new BigInteger("-1");
        BigInteger gcd = this.getGCD(this.getNumerator(), this.getDenominator());
        this.numerator = (this.getNumerator().divide(gcd));
        this.denominator = (this.getDenominator().divide(gcd));
        if(this.getDenominator().compareTo(zero) < 0) {
            this.numerator.multiply(negOne);
            this.denominator.multiply(negOne);
        }
    }

    // returns the greatest common denominator of 'a' and 'b' using Euclid's algorithm
    public BigInteger getGCD(BigInteger a, BigInteger b) {
        BigInteger r = a.remainder(b);
        BigInteger zero = new BigInteger("0");
        if(!r.equals(zero)) {
            return this.getGCD(b, r);
        }
        return b;
    }
}
