import java.util.Scanner;

/**
 * log_2(n)의 floor를 계산하는 프로그램
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class Floor {
	public static void main(String... args) {
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.print("N: ");
			long n = input.nextLong();
			floorLog_2(n);
			binaryFloorLog_2(n);
		}
	}

	private static void floorLog_2(long n) {
		long e = -1, k = 1, count = 0;

		while (k <= n) {
			k *= 2;
			e++;
			count++;
		}

		System.out.println("=========== Linear");
		System.out.println("Answer: " + e);
		System.out.println("Count: " + count);
	}

	private static void binaryFloorLog_2(long n) {
		long end = 1, count = 0;
		long k = 2, start = 0, mid = 0;

		while (k <= n) {
			k *= k;
			end *= 2;
			count++;
		}

		start = end / 2;
		mid = (start + end) / 2;
		k /= Math.pow(2, mid - start);

		while (start < mid) {
			if (k > n) {
				end = mid;
				mid = (start + end) / 2;
				k /= (int) Math.pow(2, (end - mid));
			} else {
				start = mid;
				mid = (start + end) / 2;
				k *= (int) Math.pow(2, (mid - start));
			}

			count++;
		}

		System.out.println("=========== Binary");
		System.out.println("Answer: " + mid);
		System.out.println("Count: " + count);
	}

	private static double log_2(double n) {
		return Math.log(n) / Math.log(2.0);
	}
}