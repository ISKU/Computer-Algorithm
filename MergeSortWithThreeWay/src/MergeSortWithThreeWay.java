import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MergeSortWithThreeWay {

	private static int mergeCount;

	public static void main(String... args) throws IOException {
		int[] data = readArrayAndInitData();

		long startTime = System.nanoTime();
		mergeCount = 0;
		threeWayMergeSort(data, new int[data.length], 0, data.length - 1);
		long endTime = System.nanoTime();

		writeArray(data);
		System.out.println("Execution Time : " + (endTime - startTime));
		System.out.println("Merge Count: " + mergeCount);
	}

	private static void threeWayMergeSort(int[] array, int[] tempArray, int leftSideStart, int rightSideEnd) {
		int currentArraySize = rightSideEnd - leftSideStart + 1;
		int centerSideStart = leftSideStart + (currentArraySize / 3);
		int rightSideStart = leftSideStart + (currentArraySize / 3 * 2);

		if (currentArraySize <= 1) {
			return;
		} else if (currentArraySize <= 2) {
			if (array[leftSideStart] > array[leftSideStart + 1]) {
				int tempValue = array[leftSideStart];
				array[leftSideStart] = array[leftSideStart + 1];
				array[leftSideStart + 1] = tempValue;
			}
		} else {
			threeWayMergeSort(array, tempArray, leftSideStart, centerSideStart - 1);
			threeWayMergeSort(array, tempArray, centerSideStart, rightSideStart - 1);
			threeWayMergeSort(array, tempArray, rightSideStart, rightSideEnd);
			merge(array, tempArray, leftSideStart, centerSideStart, rightSideStart, rightSideEnd);
		}
	}

	private static void merge(int[] array, int[] tempArray, int indexOfLeft, int indexOfCenter, int indexOfRight, int indexOfEnd) {
		int indexOfMaxLeft = indexOfCenter - 1;
		int indexOfMaxCenter = indexOfRight - 1;
		int currentArraySize = indexOfEnd - indexOfLeft + 1;
		int tempArrayIndex = indexOfLeft;

		while (indexOfLeft <= indexOfMaxLeft && indexOfCenter <= indexOfMaxCenter && indexOfRight <= indexOfEnd) {
			if (array[indexOfLeft] < array[indexOfCenter] && array[indexOfLeft] < array[indexOfRight])
				tempArray[tempArrayIndex++] = array[indexOfLeft++];
			else if (array[indexOfCenter] < array[indexOfLeft] && array[indexOfCenter] < array[indexOfRight])
				tempArray[tempArrayIndex++] = array[indexOfCenter++];
			else if (array[indexOfRight] < array[indexOfLeft] && array[indexOfRight] < array[indexOfCenter])
				tempArray[tempArrayIndex++] = array[indexOfRight++];
		}

		if (indexOfLeft > indexOfMaxLeft) {
			while (indexOfCenter <= indexOfMaxCenter && indexOfRight <= indexOfEnd) {
				if (array[indexOfCenter] < array[indexOfRight])
					tempArray[tempArrayIndex++] = array[indexOfCenter++];
				else
					tempArray[tempArrayIndex++] = array[indexOfRight++];
			}
		} else if (indexOfCenter > indexOfMaxCenter) {
			while (indexOfLeft <= indexOfMaxLeft && indexOfRight <= indexOfEnd) {
				if (array[indexOfLeft] < array[indexOfRight])
					tempArray[tempArrayIndex++] = array[indexOfLeft++];
				else
					tempArray[tempArrayIndex++] = array[indexOfRight++];
			}
		} else if (indexOfRight > indexOfEnd) {
			while (indexOfLeft <= indexOfMaxLeft && indexOfCenter <= indexOfMaxCenter) {
				if (array[indexOfLeft] < array[indexOfCenter])
					tempArray[tempArrayIndex++] = array[indexOfLeft++];
				else
					tempArray[tempArrayIndex++] = array[indexOfCenter++];
			}
		}

		while (indexOfLeft <= indexOfMaxLeft)
			tempArray[tempArrayIndex++] = array[indexOfLeft++];
		while (indexOfCenter <= indexOfMaxCenter)
			tempArray[tempArrayIndex++] = array[indexOfCenter++];
		while (indexOfRight <= indexOfEnd)
			tempArray[tempArrayIndex++] = array[indexOfRight++];

		for (int count = 0; count < currentArraySize; count++)
			array[indexOfEnd] = tempArray[indexOfEnd--];

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
		BufferedWriter writer = new BufferedWriter(new FileWriter("hw02_00_201201356_3way_merge.txt"));
		writer.write(String.valueOf(array[0]));
		for (int index = 1; index < array.length; index++)
			writer.write("," + array[index]);
		writer.write("," + mergeCount);
		writer.close();
	}
}