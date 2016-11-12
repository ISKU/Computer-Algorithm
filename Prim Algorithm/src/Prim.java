import java.util.PriorityQueue;

/**
 * Prim's Algorithm 
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class Prim {

	private static Edge[] graph;
	private static int vertices;
	private static int startVertex;

	private static PriorityQueue<Edge> smallsetDistanceQ;
	private static boolean[] visited;

	public static void main(String[] args) {
		initialize();
		inputGraph();
		exercisePrim();
	}

	private static void exercisePrim() {
		int cost = 0;
		initializePrim();

		while (!smallsetDistanceQ.isEmpty()) {
			Edge edge = smallsetDistanceQ.poll();

			if (!visited[edge.destination]) {
				visited[edge.destination] = true;
				cost += edge.weight;
				print(edge.source, edge.destination, edge.weight);

				for (Edge vertex = graph[edge.destination]; vertex != null; vertex = vertex.edge)
					smallsetDistanceQ.add(new Edge(edge.destination, vertex.index, vertex.weight));
			}
		}

		System.out.printf("\nw<MST> = %d\n", cost);
	}

	private static void initializePrim() {
		visited = new boolean[vertices];
		visited[startVertex] = true;
		print(-65, startVertex, 0);

		for (Edge vertex = graph[0]; vertex != null; vertex = vertex.edge)
			smallsetDistanceQ.add(new Edge(startVertex, vertex.index, vertex.weight));
	}

	private static void initialize() {
		smallsetDistanceQ = new PriorityQueue<Edge>();
		vertices = 9;
		startVertex = 0;
		graph = new Edge[vertices];
	}

	private static void inputGraph() {
		// vertex adjacent to 'a'
		graph[0] = new Edge(1, graph[0], 4); // b
		graph[0] = new Edge(7, graph[0], 8); // h

		// vertex adjacent to 'b'
		graph[1] = new Edge(0, graph[1], 4); // a
		graph[1] = new Edge(2, graph[1], 8); // c
		graph[1] = new Edge(8, graph[1], 11); // h

		// vertex adjacent to 'c'
		graph[2] = new Edge(1, graph[2], 8); // b
		graph[2] = new Edge(3, graph[2], 7); // d
		graph[2] = new Edge(5, graph[2], 4); // f
		graph[2] = new Edge(8, graph[2], 2); // i

		// vertex adjacent to 'd'
		graph[3] = new Edge(2, graph[3], 7); // c
		graph[3] = new Edge(4, graph[3], 9); // e
		graph[3] = new Edge(5, graph[3], 14); // f

		// vertex adjacent to 'e'
		graph[4] = new Edge(3, graph[4], 9); // d
		graph[4] = new Edge(5, graph[4], 10); // f

		// vertex adjacent to 'f'
		graph[5] = new Edge(2, graph[5], 4); // c
		graph[5] = new Edge(3, graph[5], 14); // d
		graph[5] = new Edge(4, graph[5], 10); // e
		graph[5] = new Edge(6, graph[5], 2); // g

		// vertex adjacent to 'g'
		graph[6] = new Edge(5, graph[6], 2); // f
		graph[6] = new Edge(7, graph[6], 1); // h
		graph[6] = new Edge(8, graph[6], 6); // i

		// vertex adjacent to 'h'
		graph[7] = new Edge(0, graph[7], 8); // a
		graph[7] = new Edge(1, graph[7], 11); // b
		graph[7] = new Edge(6, graph[7], 1); // g
		graph[7] = new Edge(8, graph[7], 7); // i

		// vertex adjacent to 'i'
		graph[8] = new Edge(2, graph[8], 2); // c
		graph[8] = new Edge(6, graph[8], 6); // g
		graph[8] = new Edge(8, graph[8], 7); // h
	}

	private static void print(int source, int destination, int weight) {
		System.out.printf("w<%c,%c> = %d\n", source + 97, destination + 97, weight);
	}

	private static class Edge implements Comparable<Edge> {
		public int index;
		public int source;
		public int destination;
		public int weight;
		public Edge edge;

		public Edge(int index, Edge edge, int weight) {
			this.index = index;
			this.edge = edge;
			this.weight = weight;
		}

		public Edge(int source, int destination, int weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge compare) {
			if (this.weight < compare.weight)
				return -1;
			else if (this.weight > compare.weight)
				return 1;
			else
				return 0;
		}
	}
}