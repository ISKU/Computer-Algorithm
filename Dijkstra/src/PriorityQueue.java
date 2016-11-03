/**
 * Dijkstra를 위한 Min-Heap을 사용한 PriorityQueue 
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class PriorityQueue {

	private Edge[] tree;
	private int treeSize;

	public PriorityQueue() {
		treeSize = 0;
		tree = new Edge[50];
	}

	public void insert(Edge element) {
		if (treeSize == tree.length)
			resize();

		treeSize++;
		int currentIndex = treeSize - 1;
		int parent = parent(currentIndex);
		tree[currentIndex] = element;

		while (parent >= 0 && tree[parent].weight > element.weight) {
			elementSwap(parent, currentIndex);
			currentIndex = parent;
			parent = parent(currentIndex);
		}
	}

	public Edge extract() {
		Edge maxNode = tree[0];
		elementSwap(0, treeSize - 1);
		treeSize--;
		heapify(0);
		return maxNode;
	}

	private void heapify(int parent) {
		int left = left(parent);
		int right = right(parent);
		int largestValue;

		largestValue = (left < treeSize && tree[left].weight < tree[parent].weight) ? left : parent;
		largestValue = (right < treeSize && tree[right].weight < tree[largestValue].weight) ? right : largestValue;

		if (largestValue != parent) {
			elementSwap(largestValue, parent);
			heapify(largestValue);
		}
	}

	public boolean isEmpty() {
		return treeSize <= 0 ? true : false;
	}

	private void elementSwap(int firstIndex, int secondIndex) {
		Edge tempNode = tree[firstIndex];
		tree[firstIndex] = tree[secondIndex];
		tree[secondIndex] = tempNode;
	}

	private void resize() {
		Edge[] tempArray = new Edge[tree.length * 2];
		System.arraycopy(tree, 0, tempArray, 0, treeSize);
		tree = tempArray;
	}

	private int left(int parent) {
		return parent * 2 + 1;
	}

	private int right(int parent) {
		return parent * 2 + 2;
	}

	private int parent(int child) {
		return (child - 1) / 2;
	}
}