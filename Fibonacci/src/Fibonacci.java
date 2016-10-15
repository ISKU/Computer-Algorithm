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
	private static final BigInteger TEN = new BigInteger("10");
	private static final String LINE = "--------------------------------------------------------\n";

	public static void main(String... args) {
		Scanner input = new Scanner(System.in);

		printSelectWay();
		int selectWay = input.nextInt();
		System.out.print("Find N-th Fibonacci Number: ");
		BigInteger nth = new BigInteger(input.next());

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

		System.out.print(LINE);
		input.close();
	}

	private static BigInteger recursion(BigInteger nth) {
		if (nth.compareTo(TWO) == -1)
			return nth;
		return recursion(nth.subtract(ONE)).add(recursion(nth.subtract(TWO)));
	}

	private static BigInteger array(BigInteger nth) {
		if (nth.equals(ZERO))
			return ZERO;
		if (nth.equals(ONE))
			return ONE;

		BigInteger first = ZERO;
		BigInteger second = ONE;
		BigInteger third;

		while (!nth.equals(TWO)) {
			third = first.add(second);
			first = second;
			second = third;
			nth = nth.subtract(ONE);
		}

		return first.add(second);
	}

	private static BigInteger squaring(BigInteger nth) {
		BigInteger[][] matrix = new BigInteger[][] { { ONE, ZERO }, { ZERO, ONE } };
		BigInteger[][] matrixFibonacci = new BigInteger[][] { { ONE, ONE }, { ONE, ZERO } };

		while (!nth.equals(ZERO)) {
			if (nth.remainder(TWO).equals(ONE))
				matrix = multiplication(matrix, matrixFibonacci);
			matrixFibonacci = multiplication(matrixFibonacci, matrixFibonacci);
			nth = nth.divide(TWO);
		}

		return matrix[0][1];
	}

	private static BigInteger[][] multiplication(BigInteger[][] A, BigInteger[][] B) {
		BigInteger[][] newMatrix = new BigInteger[][] { { ZERO, ZERO }, { ZERO, ZERO } };

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 2; k++)
					newMatrix[i][j] = newMatrix[i][j].add(A[i][k].multiply(B[k][j]));

		return newMatrix;
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
		BigInteger n = ZERO;

		while (n.compareTo(nth) != 1) {
			double startTime = System.nanoTime();
			BigInteger fibonacciNumber = array(n);
			double endTime = System.nanoTime();

			printNumber(n, fibonacciNumber, endTime - startTime);
			n = n.add(ONE);
		}
	}

	private static void exerciseSquaring(BigInteger nth) {
		BigInteger n = ZERO;

		while (n.compareTo(nth) != 1) {
			double startTime = System.nanoTime();
			BigInteger fibonacciNumber = squaring(n);
			double endTime = System.nanoTime();

			printNumber(n, fibonacciNumber, endTime - startTime);
			n = n.add(ONE);
		}
	}

	private static void printNumber(BigInteger nth, BigInteger fibonacciNumber, double time) {
		if (nth.remainder(TEN).equals(ZERO))
			System.out.print(LINE);
		System.out.printf("f<%2s> = %-25s \t %.9f sec\n", nth, fibonacciNumber, time * Math.pow(10, -9));
	}

	private static void printSelectWay() {
		System.out.println("1. Recursion");
		System.out.println("2. Array");
		System.out.println("3. Recursive Squaring");
	}
}