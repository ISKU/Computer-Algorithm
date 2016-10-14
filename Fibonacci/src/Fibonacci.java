import java.util.Scanner;
import java.math.BigInteger;

/**
 * n��° Fibonacci Number�� ���ϴ� ���α׷� 
 * �˰��� 00�� 201201356 ���ȣ
 * @author Kim Min-Ho
 */
public class Fibonacci {

	private static final BigInteger ZERO = new BigInteger("0");
	private static final BigInteger ONE = new BigInteger("1");
	private static final BigInteger TWO = new BigInteger("2");
	private static final String LINE = "--------------------------------------------------------\n";

	public static void main(String... args) {
		Scanner input = new Scanner(System.in);

		printSelectWay();
		int selectWay = input.nextInt();
		System.out.print("Find N-th Fibonacci Number: ");
		BigInteger nth = new BigInteger(input.next());
		System.out.print(LINE);

		switch (selectWay) {
		case 1:
			exerciseRecursion(nth);
			break;
		case 2:
			exerciseArray(nth);
			break;
		case 3:
			exerciseSquaring(nth);
			break;
		default:
			System.out.println("Invaild Input");
		}
	}

	private static BigInteger recursion(BigInteger nth) {
		if (nth.compareTo(TWO) == -1)
			return nth;
		return recursion(nth.subtract(ONE)).add(recursion(nth.subtract(TWO)));
	}

	private static void array(BigInteger nth) {

	}

	private static void squaring(BigInteger nth) {

	}

	private static void exerciseRecursion(BigInteger nth) {
		BigInteger n = ZERO;

		while (n.compareTo(nth) != 1) {
			double startTime = System.nanoTime();
			BigInteger fibonacciNumber = recursion(n);
			double endTime = System.nanoTime();

			printNumber(n, fibonacciNumber, endTime - startTime);
			n = n.add(ONE);
		}
	}

	private static void exerciseArray(BigInteger nth) {

	}

	private static void exerciseSquaring(BigInteger nth) {

	}

	private static void printNumber(BigInteger nth, BigInteger fibonacciNumber, double time) {
		System.out.printf("f<%2s> = %-25s \t %.9f sec\n", nth, fibonacciNumber, time * Math.pow(10, -9));
	}

	private static void printSelectWay() {
		System.out.println("1. Recursion");
		System.out.println("2. Array");
		System.out.println("3. Recursive Squaring");
	}
}