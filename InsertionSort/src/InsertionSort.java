import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InsertionSort {
	public static void main(String... args) throws IOException {
		int[] data = initData(readArray());
		
		long startTime = System.nanoTime();
		insertionSort(data); // exercise insertion sort
		long endTime = System.nanoTime();

		writeArray(data); // output file
		System.out.println("Execution Time: " + (endTime - startTime));
	}

	private static void insertionSort(int[] array) {
		for (int count = 1; count < array.length; count++) {
			int key = array[count];
			int index = count - 1;

			while (index >= 0 && array[index] > key)
				array[index + 1] = array[index--];
			array[index + 1] = key;
		}
	}

	private static String[] readArray() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data02.txt"));
		//BufferedReader reader = new BufferedReader(new FileReader("hw02_10man.txt"));
		//BufferedReader reader = new BufferedReader(new FileReader("hw02_100man.txt"));
		String[] readData = reader.readLine().split(",");
		reader.close();
		return readData;
	}

	private static void writeArray(int[] array) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("hw02_00_201201356_insertion.txt"));
		writer.write(String.valueOf(array[0]));
		for (int index = 1; index < array.length; index++)
			writer.write("," + array[index]);
		writer.close();
	}

	private static int[] initData(String[] data) {
		int integerDataArray[] = new int[data.length];
		for (int index = 0; index < data.length; index++)
			integerDataArray[index] = Integer.parseInt(data[index]);
		return integerDataArray;
	}
}