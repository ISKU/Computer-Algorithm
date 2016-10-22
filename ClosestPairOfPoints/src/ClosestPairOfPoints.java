import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Closest Pair Of Points Problem
 * æÀ∞Ì∏Æ¡Ú 00π› 201201356 ±ËπŒ»£
 * @author Kim Min-Ho
 */
public class ClosestPairOfPoints {
	public static void main(String... args) throws IOException {
		Point points[] = readArrayAndInitData();

		quickSort(points, 0, points.length - 1, true);
		double smallestDistance = closestPair(points, 0, points.length - 1);
		System.out.printf("%.3f", (smallestDistance == Double.MAX_VALUE) ? 0 : smallestDistance);
	}

	private static double closestPair(Point[] array, int start, int end) {
		double delta = Double.MAX_VALUE;
		int length = end - start + 1;

		if (length <= 2)
			return distance(array[start], array[end]);

		int mid = (start + end) / 2;
		double alpha = closestPair(array, start, mid);
		double beta = closestPair(array, mid + 1, end);
		delta = Math.min(alpha, beta);

		int lengthOfBand = 0;
		Point[] band = new Point[length];
		for (int index = start; index <= end; index++)
			if (array[index].x - array[mid].x <= delta)
				band[lengthOfBand++] = array[index];

		quickSort(band, 0, lengthOfBand - 1, false);

		for (int i = 0; i < lengthOfBand; i++) {
			for (int j = i + 1; j < i + 12 && j < lengthOfBand; j++) {
				double tempDistance = distance(band[i], band[j]);

				if (tempDistance < delta) {
					delta = tempDistance;
					break;
				}
			}
		}

		return delta;
	}

	private static double distance(Point first, Point second) {
		double distance = Math.sqrt(square(first.x - second.x) + square(first.y - second.y));
		return (distance == 0) ? Double.MAX_VALUE : distance;
	}

	private static double square(double x) {
		return x * x;
	}

	private static void quickSort(Point[] array, int low, int high, boolean coordinate) {
		if (array == null || array.length == 0)
			return;
		if (low >= high)
			return;

		int middle = low + (high - low) / 2;
		double pivot = coordinate ? array[middle].x : array[middle].y;
		int i = low, j = high;

		while (i <= j) {
			while ((coordinate ? array[i].x : array[i].y) < pivot)
				i++;
			while ((coordinate ? array[j].x : array[j].y) > pivot)
				j--;

			if (i <= j) {
				Point temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
		}

		if (low < j)
			quickSort(array, low, j, coordinate);
		if (high > i)
			quickSort(array, i, high, coordinate);
	}

	private static class Point {
		public double x;
		public double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	private static Point[] readArrayAndInitData() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data07_closest.txt"));
		ArrayList<Point> fileData = new ArrayList<Point>();
		String readData = null;

		while ((readData = reader.readLine()) != null) {
			String[] coordinate = readData.split(",");
			fileData.add(new Point(Double.parseDouble(coordinate[0]), Double.parseDouble(coordinate[1])));
		}
		reader.close();

		Point[] dataArray = new Point[fileData.size()];
		for (int index = 0, size = fileData.size(); index < size; index++)
			dataArray[index] = fileData.get(index);
		return dataArray;
	}
}