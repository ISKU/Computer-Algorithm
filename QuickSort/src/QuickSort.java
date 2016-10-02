import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class QuickSort {
	public static void main(String... args) throws IOException {
		int[] data = readArrayAndInitData(); // input file

		long startTime = System.nanoTime();
		quickSort(data, 0, data.length - 1); // exercise quick sort
		long endTime = System.nanoTime();

		writeArray(data); // output file
		System.out.println("Execution Time: " + (endTime - startTime));
	}

	private static void quickSort(int[] array, int left, int right) {
		if (left < right) {
			int center = partition(array, left, right);
			quickSort(array, left, center - 1);
			quickSort(array, center + 1, right);
		}
	}

	private static int partition(int[] array, int start, int end) {
		int pivot = start;
		int left = start;
		int right = end;

		while (left < right) {
			while (array[left] <= array[pivot] && left < right)
				left++;
			while (array[right] >= array[pivot] && left < right)
				right--;

			if (left < right) {
				System.out.println(left + " " + right);
				swap(array, left, right);
			}
		}

		swap(array, pivot, right);
		return left;
	}

	private static void swap(int[] array, int firstIndex, int secondIndex) {
		int tempValue = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = tempValue;
	}

	private static int[] readArrayAndInitData() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data04.txt"));
		StringTokenizer readData = new StringTokenizer(reader.readLine());
		ArrayList<Integer> integerData = new ArrayList<Integer>(100000000);
		reader.close();

		while (readData.hasMoreTokens())
			integerData.add(Integer.parseInt(readData.nextToken(",")));

		int[] integerDataArray = new int[integerData.size()];
		for (int index = 0, size = integerData.size(); index < size; index++)
			integerDataArray[index] = integerData.get(index);
		return integerDataArray;
	}

	private static void writeArray(int[] array) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("hw03_00_201201356_quick.txt"));
		writer.write(String.valueOf(array[0]));
		for (int index = 1; index < array.length; index++)
			writer.write("," + array[index]);
		writer.close();
	}
}