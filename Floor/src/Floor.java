import java.util.Scanner;

/**
 * log_2(n)�� floor�� ����ϴ� ���α׷� 
 * �˰��� 00�� 201201356 ���ȣ
 * @author Kim Min-Ho
 */
public class Floor {
	public static void main(String... args) {
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.print("N: ");
			double n = input.nextDouble();
			floorLog_2(n);
		}
	}

	private static void floorLog_2(double n) {
		int e = -1, k = 1, count = 0;

		while (k <= n) {
			k *= 2;
			e++;
			count++;
		}

		System.out.println("=========== Linear");
		System.out.println("Answer: " + e);
		System.out.println("Count: " + count);
	}

	private static double log_2(double n) {
		return Math.log(n) / Math.log(2.0);
	}
}