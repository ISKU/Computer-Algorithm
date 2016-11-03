import java.util.Scanner;

/**
 * Dijkstra Algorithm �˰��� 00�� 201201356 ���ȣ
 * 
 * @author Kim Min-Ho
 */
public class Dijkstra {

	private static Scanner input = new Scanner(System.in);
	private static final int INFINITY = Integer.MAX_VALUE;

	private static PriorityQueue pQueue;
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
		exerciseDijkstra();
		input.close();
	}

	private static void exerciseDijkstra() {
		initializationDijkstra();

		while (!pQueue.isEmpty()) {
			int currentVertex = pQueue.extract().index;
			visitedVertex[currentVertex] = true;

			for (Edge vertex = graph[currentVertex]; vertex != null; vertex = vertex.edge)
				if (!visitedVertex[vertex.index] && distance[currentVertex] + vertex.weight < distance[vertex.index]) {
					distance[vertex.index] = distance[currentVertex] + vertex.weight;
					previousVertex[vertex.index] = currentVertex;
					pQueue.insert(new Edge(vertex.index, distance[vertex.index]));
				}
		}

		for (int i = 0; i < numberOfVertices; i++)
			System.out.print(previousVertex[i] + " ");
		System.out.println();

		for (int i = 0; i < numberOfVertices; i++) {
			System.out.printf("%d: �Ÿ� %d / %s", i, distance[i], previousVertex[i] == INFINITY ? "�����" : i);
			printPath(previousVertex[i]);
			System.out.println();
		}

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
		pQueue = new PriorityQueue();

		for (int i = 0; i < numberOfVertices; i++)
			if (i != firstVertex)
				distance[i] = INFINITY;
		for (int i = 0; i < numberOfVertices; i++)
			previousVertex[i] = INFINITY;

		pQueue.insert(new Edge(firstVertex, 0));
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

}