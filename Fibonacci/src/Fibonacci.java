import java.util.Scanner;
import java.math.BigInteger;

/**
 * n번째 Fibonacci Number를 구하는 프로그램 
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class Fibonacci {

	private static final BigInteger ZERO = new BigInteger("0");
	private static final BigInteger ONE = new BigInteger("1");
	private static final BigInteger TWO = new BigInteger("2");

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
	}

	private static void exerciseRecursion(BigInteger nth) {

	}
	
	private static void exerciseArray(BigInteger nth) {

	}
	
	private static void exerciseSquaring(BigInteger nth) {

	}

	private static void printSelectWay() {
		System.out.println("1. Recursion");
		System.out.println("2. Array");
		System.out.println("3. Recursive Squaring");
	}
}