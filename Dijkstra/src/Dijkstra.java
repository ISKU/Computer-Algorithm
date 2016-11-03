import java.util.Scanner;

/**
 * Dijkstra Algorithm 
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class Dijkstra {

	private static Scanner input = new Scanner(System.in);
	private static final int INFINITY = Integer.MAX_VALUE;

	private static Edge[] graph;
	private static int numberOfVertices;
	private static int firstVertex;
	private static int[] distance;
	private static int[] previousVertex;
	private static boolean[] visitedVertex;

	public static void main(String[] args) {
		inputVertices();
		inputGraphMatrix();
		inputFirstVertex();

		exerciseDijkstra(firstVertex);

		input.close();
	}

	private static void exerciseDijkstra(int firstIndex) {
		initializationDijkstra();

		while (firstIndex != -1) {
			visitedVertex[firstIndex] = true;
			for (Edge vertex = graph[firstIndex]; vertex != null; vertex = vertex.next)
				if (!visitedVertex[vertex.vertex] && distance[firstIndex] + vertex.weight < distance[vertex.vertex]) {
					distance[vertex.vertex] = distance[firstIndex] + vertex.weight;
					previousVertex[vertex.vertex] = firstIndex;
				}
			firstIndex = findSmallestDist(distance, visitedVertex);
		}

		for (int i = 0; i < numberOfVertices; i++)
			System.out.print(previousVertex[i] + " ");
		System.out.println();

		for (int i = 0; i < numberOfVertices; i++) {
			System.out.printf("%d: 거리 %d / %s", i, distance[i], previousVertex[i] == INFINITY ? "출발점" : i);
			printPath(previousVertex[i]);
			System.out.println();
		}

	}

	private static int findSmallestDist(int[] dist, boolean[] mark) {
		int min = INFINITY;
		int distindex = -1;

		for (int i = 0; i < numberOfVertices; i++)
			if (!mark[i] && dist[i] < min) {
				min = dist[i];
				distindex = i;
			}

		return distindex;
	}

	private static void printPath(int prevVertex) {
		if (prevVertex == INFINITY)
			return;

		System.out.print("<-" + prevVertex);
		printPath(previousVertex[prevVertex]);
	}

	private static void initializationDijkstra() {
		distance = new int[numberOfVertices];
		previousVertex = new int[numberOfVertices];
		visitedVertex = new boolean[numberOfVertices];

		for (int i = 0; i < numberOfVertices; i++)
			if (i != firstVertex)
				distance[i] = INFINITY;

		for (int i = 0; i < numberOfVertices; i++)
			previousVertex[i] = INFINITY;
	}

	private static void inputVertices() {
		System.out.println("Enter the number of vertices: ");
		numberOfVertices = input.nextInt();
	}

	private static void inputGraphMatrix() {
		graph = new Edge[numberOfVertices];

		System.out.println("Enter the cost matrix: ");
		for (int firstVertex = 0; firstVertex < numberOfVertices; firstVertex++) {
			for (int secondVertex = 0; secondVertex < numberOfVertices; secondVertex++) {
				int weight = input.nextInt();
				if (weight == 0)
					continue;
				graph[firstVertex] = new Edge(secondVertex, graph[firstVertex], weight);
			}
		}
	}

	private static void inputFirstVertex() {
		System.out.println("\nEnter the source matrix: ");
		firstVertex = input.nextInt();
	}

	private static class Edge {
		public int vertex;
		public int weight;
		public Edge next;

		public Edge(int vertex, Edge next, int weight) {
			this.vertex = vertex;
			this.next = next;
			this.weight = weight;
		}
	}
}