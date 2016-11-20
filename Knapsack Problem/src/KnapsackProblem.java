import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * 0-1 Knapsack Problem Dynamic Programming 
 * 알고리즘 00반 201201356 김민호
 * @author KMH
 */
public class KnapsackProblem {

	private static Item[] items;
	private static int[][] table;
	private static boolean[][] composition;

	private static int bagSize;
	private static int numberOfItems = 0;

	public static void main(String[] args) throws IOException {
		inputItem();
		inputBagSize();
		run();
		outputMatrix();
		outputItem();
	}

	private static void run() {
		for (int i = 1; i <= numberOfItems; i++)
			for (int j = 1; j <= bagSize; j++)
				if (items[i].weight > j)
					table[i][j] = table[i - 1][j];
				else {
					if (items[i].value + table[i - 1][j - items[i].weight] > table[i - 1][j]) {
						table[i][j] = items[i].value + table[i - 1][j - items[i].weight];
						composition[i][j] = true;
					} else
						table[i][j] = table[i - 1][j];
				}
	}

	private static void outputItem() {
		System.out.printf("MAX: %d\n", table[numberOfItems][bagSize]);
		System.out.printf("ITEM: ");
		for (int i = numberOfItems, j = bagSize; i > 0; i--)
			if (composition[i][j]) {
				System.out.printf("%d ", items[i].index);
				j -= items[i].weight;
			}
		System.out.println();
	}

	private static void outputMatrix() {
		for (int i = 0; i <= numberOfItems; i++) {
			for (int j = 0; j <= bagSize; j++)
				System.out.printf("%-3d ", table[i][j]);
			System.out.println();
		}
		System.out.println();
	}

	private static void inputBagSize() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("배낭의 사이즈를 입력해주세요 (0~50): ");
		bagSize = Integer.parseInt(input.readLine());
		table = new int[numberOfItems + 1][bagSize + 1];
		composition = new boolean[numberOfItems + 1][bagSize + 1];
	}

	private static void inputItem() throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("data11.txt"));
		String line = null;

		while ((line = input.readLine()) != null)
			numberOfItems++;
		input.close();

		items = new Item[numberOfItems + 1];
		input = new BufferedReader(new FileReader("data11.txt"));
		for (int index = 1; (line = input.readLine()) != null; index++) {
			String[] item = line.split(",");
			items[index] = new Item(Integer.parseInt(item[0]), Integer.parseInt(item[1]), Integer.parseInt(item[2]));
		}
		input.close();
	}

	private static class Item {
		public int index;
		public int value;
		public int weight;

		public Item(int index, int value, int weight) {
			this.index = index;
			this.value = value;
			this.weight = weight;
		}
	}
}