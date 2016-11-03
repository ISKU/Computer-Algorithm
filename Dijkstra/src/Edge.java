public class Edge {
	public int index;
	public int weight;
	public Edge edge;

	public Edge(int vertex, Edge edge, int weight) {
		this.index = vertex;
		this.edge = edge;
		this.weight = weight;
	}

	public Edge(int vertex, int weight) {
		this.index = vertex;
		this.weight = weight;
	}
}