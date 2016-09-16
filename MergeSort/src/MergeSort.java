import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MergeSort {

	private static int mergeCount;

	public static void main(String... args) throws IOException {
		int[] data = readArrayAndInitData();

		long startTime = System.nanoTime();
		mergeCount = 0;
		mergeSort(data, new int[data.length], 0, data.length - 1); // exercise merge sort
		long endTime = System.nanoTime();

		writeArray(data); // output file
		System.out.println("Execution Time: " + (endTime - startTime));
		System.out.println("Merge Count: " + mergeCount);
	}

	private static void mergeSort(int[] array, int[] tempArray, int leftSide, int rightSide) {
		if (leftSide < rightSide) {
			int indexOfMid = (leftSide + rightSide) / 2;
			mergeSort(array, tempArray, leftSide, indexOfMid);
			mergeSort(array, tempArray, indexOfMid + 1, rightSide);
			merge(array, tempArray, leftSide, indexOfMid + 1, rightSide);
		}
	}

	private static void merge(int[] array, int[] tempArray, int indexOfLeft, int indexOfRight, int indexOfMaxRight) {
		int indexOfMaxLeft = indexOfRight - 1;
		int currentArraySize = indexOfMaxRight - indexOfLeft + 1;
		int tempArrayIndex = indexOfLeft;

		while (indexOfLeft <= indexOfMaxLeft && indexOfRight <= indexOfMaxRight) {
			if (array[indexOfLeft] < array[indexOfRight])
				tempArray[tempArrayIndex++] = array[indexOfLeft++];
			else
				tempArray[tempArrayIndex++] = array[indexOfRight++];
		}

		if (indexOfLeft <= indexOfMaxLeft) {
			while (indexOfLeft <= indexOfMaxLeft)
				tempArray[tempArrayIndex++] = array[indexOfLeft++];
		} else {
			while (indexOfRight <= indexOfMaxRight)
				tempArray[tempArrayIndex++] = array[indexOfRight++];
		}

		for (int count = 0; count < currentArraySize; count++)
			array[indexOfMaxRight] = tempArray[indexOfMaxRight--];

		mergeCount++;
	}

	private static int[] readArrayAndInitData() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data02.txt"));
		// BufferedReader reader = new BufferedReader(new FileReader("hw02_10man.txt"));
		// BufferedReader reader = new BufferedReader(new FileReader("hw02_100man.txt"));
		// BufferedReader reader = new BufferedReader(new FileReader("hw02_1000man.txt"));
		// BufferedReader reader = new BufferedReader(new FileReader("hw02_uk.txt"));
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
		BufferedWriter writer = new BufferedWriter(new FileWriter("hw02_00_201201356_merge.txt"));
		writer.write(String.valueOf(array[0]));
		for (int index = 1; index < array.length; index++)
			writer.write("," + array[index]);
		writer.write("," + mergeCount);
		writer.close();
	}
}
