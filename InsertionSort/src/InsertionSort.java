import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class InsertionSort {
	public static void main(String... args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data02.txt"));
		int[] data = initData(reader.readLine().split(","));
		reader.close();

		printArray(data); // print before sorting

		long startTime = System.nanoTime();
		insertionSort(data); // exercise insertion sort
		long endTime = System.nanoTime();

		printArray(data); // print after sorting

		writeArray(data); // output file
		System.out.println("Execution Time: " + (endTime - startTime));
	}

	private static void insertionSort(int[] array) {
		for (int j = 1; j < array.length; j++) {
			int key = array[j];
			int i = j - 1;

			while (i >= 0 && array[i] > key)
				array[i + 1] = array[i--];
			array[i + 1] = key;
		}
	}

	private static int[] initData(String[] data) {
		int integerDataArray[] = new int[data.length];
		for (int index = 0; index < data.length; index++)
			integerDataArray[index] = Integer.parseInt(data[index]);
		return integerDataArray;
	}

	private static void writeArray(int[] array) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("hw02_00_201201356_insertion.txt"));
		writer.write(String.valueOf(array[0]));
		for (int index = 1; index < array.length; index++)
			writer.write("," + array[index]);
		writer.close();
	}

	private static void printArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}
}