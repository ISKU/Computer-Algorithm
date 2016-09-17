import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 알고리즘 00반
 * 201201356 김민호
 * 
 * @author Kim Min-Ho
 *
 */
public class InsertionSortWithBinarySearch {
	public static void main(String... args) throws IOException {
		int[] data = initData(readArray());
		
		long startTime = System.nanoTime();
		insertionSortWithBinarySearch(data); // exercise binary-insertion sort
		long endTime = System.nanoTime();

		writeArray(data); // output file
		System.out.println("Execution Time: " + (endTime - startTime));
	}

	private static void insertionSortWithBinarySearch(int[] array) {
		for (int count = 1; count < array.length; count++) {
			int key = array[count];
			int indexOfLow = 0;
			int indexOfHigh = count - 1;
			int findIndex = (indexOfLow + indexOfHigh) / 2;

			while (true) {
				if (indexOfLow > indexOfHigh) {
					findIndex = indexOfLow;
					break;
				}

				if (array[findIndex] < key)
					indexOfLow = findIndex + 1;
				else if (array[findIndex] > key)
					indexOfHigh = findIndex - 1;
				else
					break;

				findIndex = (indexOfLow + indexOfHigh) / 2;
			}

			for (int index = count; index > findIndex; index--)
				array[index] = array[index - 1];
			array[findIndex] = key;
		}
	}

	private static int[] initData(String[] data) {
		int integerDataArray[] = new int[data.length];
		for (int index = 0; index < data.length; index++)
			integerDataArray[index] = Integer.parseInt(data[index]);
		return integerDataArray;
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
		BufferedWriter writer = new BufferedWriter(new FileWriter("hw02_00_201201356_binary_insertion.txt"));
		writer.write(String.valueOf(array[0]));
		for (int index = 1; index < array.length; index++)
			writer.write("," + array[index]);
		writer.close();
	}
}