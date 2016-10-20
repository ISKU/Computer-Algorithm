import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CountingInversions {
	
	private static int numberOfInversions = 0;
	
	public static void main(String... args) throws IOException {
		int[] data = readArrayAndInitData();
		sortAndCount(data, new int[data.length], 0, data.length - 1); // counting inversions
		System.out.println(numberOfInversions);
	}

	private static void sortAndCount(int[] array, int[] tempArray, int leftSide, int rightSide) {
		if (leftSide < rightSide) {
			int indexOfMid = (leftSide + rightSide) / 2;
			sortAndCount(array, tempArray, leftSide, indexOfMid);
			sortAndCount(array, tempArray, indexOfMid + 1, rightSide);
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
			else {
				tempArray[tempArrayIndex++] = array[indexOfRight++];
				numberOfInversions += indexOfMaxRight - tempArrayIndex;
			}
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
	}
	
	private static int[] readArrayAndInitData() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data07_inversion.txt"));
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
}
