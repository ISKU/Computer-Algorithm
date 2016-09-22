public class MaxHeap {

	private Node[] tree;
	private int treeSize;

	public MaxHeap() {
		// empty constructor
	}

	public void buildMaxHeap(Node[] array) {
		this.treeSize = array.length;
		this.tree = new Node[treeSize];
		System.arraycopy(array, 0, tree, 0, treeSize);

		for (int index = treeSize / 2 - 1; index >= 0; index--)
			heapify(index);
	}

	public void heapify(int parent) {
		int left = left(parent);
		int right = right(parent);
		int largestValue;

		largestValue = (left < treeSize && tree[left].value > tree[parent].value) ? left : parent;
		largestValue = (right < treeSize && tree[right].value > tree[largestValue].value) ? right : largestValue;

		if (largestValue != parent) {
			elementSwap(largestValue, parent);
			heapify(largestValue);
		}
	}

	public void sort() {
		int tempTreeSize = treeSize;

		while (treeSize-- > 0) {
			elementSwap(0, treeSize);
			heapify(0);
		}
		
		treeSize = tempTreeSize;
	}

	public int getSize() {
		return this.treeSize;
	}

	private void elementSwap(int firstIndex, int secondIndex) {
		Node tempNode = tree[firstIndex];
		tree[firstIndex] = tree[secondIndex];
		tree[secondIndex] = tempNode;
	}

	private int left(int parent) {
		return parent * 2 + 1;
	}

	private int right(int parent) {
		return parent * 2 + 2;
	}

	@Override
	public String toString() {
		StringBuilder data = new StringBuilder();
		for (int index = 0; index < treeSize; index++)
			data.append(tree[index].value + " " + tree[index].subject + "\n");
		return data.toString();
	}
}