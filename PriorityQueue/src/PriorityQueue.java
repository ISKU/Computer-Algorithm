public class PriorityQueue {

	private Node[] tree;
	private int treeSize;

	public PriorityQueue(Node[] array) {
		buildMaxHeap(array);
	}

	private void buildMaxHeap(Node[] array) {
		this.treeSize = array.length;
		this.tree = new Node[treeSize];
		System.arraycopy(array, 0, tree, 0, treeSize);

		for (int index = treeSize / 2 - 1; index >= 0; index--)
			heapify(index);
	}

	private void heapify(int parent) {
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

	public void insert(Node element) {
		resize();
		int currentIndex = treeSize - 1;
		int parent = parent(currentIndex);
		tree[currentIndex] = element;

		while (parent >= 0 && tree[parent].value < element.value) {
			elementSwap(parent, currentIndex);
			currentIndex = parent;
			parent = parent(currentIndex);
		}
	}

	public Node extract_max() {
		Node maxNode = tree[0];
		elementSwap(0, treeSize - 1);
		treeSize--;
		heapify(0);
		return maxNode;
	}

	public void increaseKey(int index, int key) { 
		int currentIndex = index - 1;
		int parent = parent(index - 1);
		tree[index - 1].value = key;
		
		while (parent >= 0 && tree[parent].value < tree[currentIndex].value) {
			elementSwap(parent, currentIndex);
			currentIndex = parent;
			parent = parent(currentIndex);
		}
	}

	public void delete(int index) {
		elementSwap(index - 1, treeSize - 1);
		treeSize--;
		heapify(index - 1);
	}

	public Node getMax() {
		return tree[0];
	}

	public int getSize() {
		return this.treeSize;
	}

	private void elementSwap(int firstIndex, int secondIndex) {
		Node tempNode = tree[firstIndex];
		tree[firstIndex] = tree[secondIndex];
		tree[secondIndex] = tempNode;
	}

	private void resize() {
		Node[] tempArray = new Node[treeSize + 1];
		System.arraycopy(tree, 0, tempArray, 0, treeSize++);
		tree = tempArray;
	}

	private int left(int parent) {
		return parent * 2 + 1;
	}

	private int right(int parent) {
		return parent * 2 + 2;
	}

	private int parent(int child) {
		return child / 2 - 1;
	}

	@Override
	public String toString() {
		StringBuilder data = new StringBuilder();
		for (int index = 0; index < treeSize; index++)
			data.append(tree[index].value + ", " + tree[index].subject + "\n");
		return data.toString();
	}
}
