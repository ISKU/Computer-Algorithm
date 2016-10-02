﻿import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Random;

/**
 * Quick Sort With Random Pivot Algorithm 
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class QuickSortWithRandomPivot {
	public static void main(String... args) throws IOException {
		int[] data = readArrayAndInitData(); // input file

		long startTime = System.nanoTime();
		quickSort(data, 0, data.length - 1); // exercise quick sort with random pivot
		long endTime = System.nanoTime();

		writeArray(data); // output file
		System.out.println("Execution Time: " + (endTime - startTime));
	}

	private static void quickSort(int[] array, int left, int right) {
		if (left < right) {
			int indexOfPivot = randomizedPartition(array, left, right);
			quickSort(array, left, indexOfPivot - 1);
			quickSort(array, indexOfPivot + 1, right);
		}
	}

	private static int randomizedPartition(int[] array, int start, int end) {
		int first = getRandomIndex(start, end);
		int second = getRandomIndex(start, end);
		int third = getRandomIndex(start, end);
		int pivotIndex = (first < second && second < third) ? second : (second < first && first < third) ? first : third;

		int pivot = array[pivotIndex];
		int left = start + 1;
		int right = end;

		swap(array, start, pivotIndex);
		while (left <= right) {
			while (left <= end && array[left] <= pivot)
				left++;
			while (array[right] > pivot)
				right--;
			if (left < right)
				swap(array, left++, right--);
		}

		swap(array, start, right);
		return right;
	}

	public static int getRandomIndex(int start, int end) {
		return (int) (Math.random() * (end - start + 1)) + start;
	}

	private static void swap(int[] array, int leftIndex, int rightIndex) {
		int tempValue = array[leftIndex];
		array[leftIndex] = array[rightIndex];
		array[rightIndex] = tempValue;
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
		BufferedWriter writer = new BufferedWriter(new FileWriter("hw03_00_201201356_quickRandom.txt"));
		writer.write(String.valueOf(array[0]));
		for (int index = 1; index < array.length; index++)
			writer.write("," + array[index]);
		writer.close();
	}
}