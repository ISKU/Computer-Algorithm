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

		System.out.println("----------- Linear");
		System.out.println("Answer: " + e);
		System.out.println("Count: " + count);
	}

	private static void binaryFloorLog_2(long n) {
		long[] arrayPower = new long[33];
		long k = 2;
		int e = 1;
		int start = 0, mid = 0, count = 0;
		arrayPower[0] = 1;

		while (k <= n && k != 0) {
			arrayPower[e] = k;
			k *= k;
			e *= 2;
			count++;
		}

		start = e / 2;
		mid = (start + e) / 2;
		k = (k == 0) 
			? arrayPower[32] * arrayPower[(mid - start)] 
			: k / arrayPower[(mid - start)];

		while (start < mid) {
			if (k > n) {
				e = mid;
				mid = (start + e) / 2;
				k /= arrayPower[e - mid];
			} else if (k < n) {
				start = mid;
				mid = (start + e) / 2;
				k *= arrayPower[mid - start];
			} else
				break;

			count++;
		}

		System.out.println("----------- Binary");
		System.out.println("Answer: " + mid);
		System.out.println("Count: " + count);
		System.out.println("==================");
	}

	private static double log_2(double n) {
		return Math.log(n) / Math.log(2.0);
	}
}