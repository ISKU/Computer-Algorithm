/**
 * Graph의 Edge의 정보를 담고 있는 클래스
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
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