
public class Main {

    public static void main(String[] args) {
	Fraction fraction1 = new Fraction(5, 8);
	Fraction fraction2 = new Fraction(1, 5);
	Fraction sum = fraction1.add(fraction2);
	System.out.println("sum: " + sum.getString());

    }
}
