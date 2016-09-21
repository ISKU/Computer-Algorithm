import java.util.Arrays;

public class MaxHeap {

	private int[] tree;
	private int treeSize;

	public MaxHeap() {
		// empty constructor
	}

	public void buildMaxHeap(int[] array) {
		this.treeSize = array.length;
		this.tree = new int[treeSize];
		System.arraycopy(array, 0, tree, 0, treeSize);
		
		for (int index = treeSize / 2 - 1; index > 0; index--)
			heapify(index);
	}

	public void heapify(int parent) {
		int left = left(parent);
		int right = right(parent);
		int largestValue;

		largestValue = (left < treeSize && tree[left] > tree[parent]) ? left : parent;
		largestValue = (right < treeSize && tree[right] > tree[largestValue]) ? right : largestValue;

		if (largestValue != parent) {
			elementSwap(largestValue, parent);
			heapify(largestValue);
		}
	}
	
	public int getSize() {
		return this.treeSize;
	}
	
	private void elementSwap(int firstIndex, int secondIndex) {
		int tempValue = tree[firstIndex];
		tree[firstIndex] = tree[secondIndex];
		tree[secondIndex] = tempValue;
	}

	private int left(int parent) {
		return parent * 2 + 1;
	}

	private int right(int parent) {
		return parent * 2 + 2;
	}

	@Override
	public String toString() {
		return Arrays.toString(tree);
	}
}
