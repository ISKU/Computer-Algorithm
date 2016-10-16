import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

/**
 * 2차원 평명 위에 직사각형의 빌딩의 위치와 높이가 주어졌을 때 
 * 감추어지는 선을 제외한 빌딩의 skyline을 계산하는 문제
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class SkyLine {
	public static void main(String... args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		StringTokenizer parser = new StringTokenizer(input.readLine());
		PriorityQueue<Building> pQueue = new PriorityQueue<Building>();

		int testCase = Integer.parseInt(parser.nextToken());
		while (testCase-- > 0) {
			parser = new StringTokenizer(input.readLine());
			pQueue.add(new Building(
					Integer.parseInt(parser.nextToken(",")),
					Integer.parseInt(parser.nextToken(",")),
					Integer.parseInt(parser.nextToken(","))));
		}

		Building first = pQueue.poll();
		int high = first.height;
		int last = first.right;
		output.append(first.left + "," + first.height + ",");

		while (!pQueue.isEmpty()) {
			Building building = pQueue.poll();

			if (building.left > last) {
				output.append(last + ",0," + building.left + "," + building.height + ",");
				high = building.height;
				last = building.right;
			} else if (building.left == last) {
				if (building.height == high)
					last = building.right;
				else {
					output.append(building.left + "," + building.height + ",");
					high = building.height;
					last = building.right;
				}
			} else {
				if (building.height > high) {
					if (building.right < last)
						pQueue.add(new Building(building.right, high, last));
					output.append(building.left + "," + building.height + ",");
					high = building.height;
					last = building.right;
				} else {
					if (building.right > last)
						pQueue.add(new Building(last, building.height, building.right));
				}
			}
		}

		output.append(last + ",0");
		System.out.print(output.toString());
	}

	private static class Building implements Comparable<Building> {
		public int left;
		public int height;
		public int right;

		public Building(int left, int height, int right) {
			this.left = left;
			this.height = height;
			this.right = right;
		}

		@Override
		public int compareTo(Building compare) {
			if (this.left < compare.left)
				return -1;
			else if (this.left > compare.left)
				return 1;
			else {
				if (this.height > compare.height)
					return -1;
				else if (this.height < compare.height)
					return 1;
				else {
					if (this.right < compare.right)
						return -1;
					else if (this.right > compare.right)
						return 1;
					else
						return 0;
				}
			}
		}
	}
}