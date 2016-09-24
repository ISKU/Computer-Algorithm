/**
 * Heap을 사용한 PriorityQueue 클래스
 * 
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class PriorityQueue {

	/**
	 * tree: PriorityQueue의 자료구조는 배열을 사용한다.
	 * treeSize: 현재 PriorityQueue의 원소 개수
	 */
	private Node[] tree;
	private int treeSize;

	/**
	 * PriorityQueue Default Constructor
	 * Tree에 삽입할 수 있는 노드의 개수는 기본적으로 100개이고, 
	 * 아무 노드도 없는 PriorityQueue를 만든다.
	 */
	public PriorityQueue() {
		treeSize = 0;
		tree = new Node[100];
	}
	
	/**
	 * PriorityQueue Constructor
	 * 사용자로부터 각 원소의 배열을 입력 받아 Heap을 사용하여 PriorityQueue를 관리한다.
	 * 
	 * @param array 각 원소를 저장할 배열
	 */
	public PriorityQueue(Node[] array) {
		treeSize = array.length;
		tree = new Node[treeSize];
		System.arraycopy(array, 0, tree, 0, treeSize);
		
		buildMaxHeap(tree);
	}
	
	/**
	 * PriorityQueue에 원소 삽입
	 * 원소의 삽입은 Tree의 가장 마지막 index에 임시로 넣어두고,
	 * 해당 index에서부터 root까지 Bottom-Up heapify를 수행한다.
	 * 
	 * @param element PriorityQueue에 삽입할 원소
	 */
	public void insert(Node element) {
		if (treeSize == tree.length)
			resize();
		
		treeSize++;
		int currentIndex = treeSize - 1;
		int parent = parent(currentIndex);
		tree[currentIndex] = element;

		while (parent >= 0 && tree[parent].value < element.value) {
			elementSwap(parent, currentIndex);
			currentIndex = parent;
			parent = parent(currentIndex);
		}
	}

	/**
	 * PriorityQueue의 우선순위가 가장 높은 원소를 반환하고 삭제한다.
	 * 
	 * @return PriorityQueue의 우선순위가 높은 원소를 반환
	 */
	public Node extractMax() {
		Node maxNode = tree[0];
		elementSwap(0, treeSize - 1);
		treeSize--;
		heapify(0);
		return maxNode;
	}
	
	/**
	 * PriorityQueue의 우선순위가 가장 높은 원소를 반환
	 * 
	 * @return Tree의 우선순위가 가장 큰 노드 반환
	 */
	public Node max() {
		return tree[0];
	}

	/**
	 * PrioirtyQueue에 있는 원소의 값을 증가시킨다.
	 * Tree에 해당 index의 value를 key로 변경 한 후,
	 * 변경되는 key의 값은 해당 현재 원소의 key 값 보다 작아질 수 없기 때문에
	 * 해당 index에서부터 root까지 Bottom-Up heapify를 수행한다.
	 * 
	 * @param index Tree의 사용자가 선택한 임의 노드의 index
	 * @param key 사용자가 선택한 임의 노드의 값을 해당 key로 변경한다.
	 */
	public void increaseKey(int index, int key) {
		int currentIndex = index - 1;
		int parent = parent(currentIndex);

		if (tree[currentIndex].value < key) {
			tree[currentIndex].value = key;
			while (parent >= 0 && tree[parent].value < tree[currentIndex].value) {
				elementSwap(parent, currentIndex);
				currentIndex = parent;
				parent = parent(currentIndex);
			}
		} else if (tree[currentIndex].value > key) {
			tree[currentIndex].value = key;
			heapify(currentIndex);
		}
	}

	/**
	 * PriorityQueue에 있는 원소를 삭제한다.
	 * Tree에 사용자가 선택한 index의 노드를 Tree의 가장 마지막 노드와 위치를 바꾼 후
	 * 바뀐 위치에서 heapify()를 수행하여 Max-Heap을 유지한다.
	 * 
	 * @param index Tree에서 삭제 할 노드의 index
	 */
	public void delete(int index) {
		elementSwap(index - 1, treeSize - 1);
		treeSize--;
		heapify(index - 1);
	}
	
	/**
	 * Tree의 노드가 없는지 확인하는 메소드
	 * 
	 * @return 트리의 노드가 없을때 true, 한 개 이상 있을 때 false
	 */
	public boolean isEmpty() {
		return treeSize <= 0 ? true : false;
	}
	
	/**
	 * @return Tree의 노드 개수를 반환
	 */
	public int getSize() {
		return this.treeSize;
	}

	/**
	 * 입력받은 배열을 root가 가장 큰 우선순위를 가지는 Max-Heap을 만드는 메소드
	 * PriorityQueue를 사용하기 위하여 사용자로부터 입력받은 배열을 가장 먼저 Max-Heap을 유지하여야 한다.
	 * 
	 * 사용자로부터 입력받은 원소의 배열을 Tree로 관리하기 위하여 같은 크기로 배열을 복사한 후 tree 변수에 저장한다.
	 * Max-Heap을 유지하기 위하여 heapify()를 실행한다.
	 * heapify()는 Tree의 leaf가 아닌 노드부터 root까지 수행하여 Max-Heap으로 정렬한다.
	 * 
	 * @param array Max-Heap으로 만들 사용자가 입력한 배열
	 */
	private void buildMaxHeap(Node[] array) {
		for (int index = treeSize / 2 - 1; index >= 0; index--)
			heapify(index);
	}

	/**
	 * Tree의 탐색을 통해 Max-Heap을 항상 유지하기 위한 메소드
	 * Tree에 노드의 삽입, 삭제, 수정이 있을 때 항상 Max-Heap 상태를 계속 유지하여야 한다.
	 * 
	 * @param parent 탐색 시작 index
	 */
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

	/**
	 * Tree 배열의 길이를 조절하는 메소드
	 * Tree에 새로운 원소가 추가하기 전에 현재 tree 배열이 모두 채워져 있으면 배열의 길이를 늘려주기 위해 사용한다.
	 */
	private void resize() {
		Node[] tempArray = new Node[tree.length * 2];
		System.arraycopy(tree, 0, tempArray, 0, treeSize);
		tree = tempArray;
	}
	
	/**
	 * Tree의 두 노드의 위치를 바꾸는 메소드
	 * 
	 * @param firstIndex Swap할 노드의 index
	 * @param secondIndex Swap할 노드의 index
	 */
	private void elementSwap(int firstIndex, int secondIndex) {
		Node tempNode = tree[firstIndex];
		tree[firstIndex] = tree[secondIndex];
		tree[secondIndex] = tempNode;
	}

	/**
	 * @param parent Tree의 index
	 * @return Tree에서 parent의 left child index를 반환
	 */
	private int left(int parent) {
		return parent * 2 + 1;
	}

	/**
	 * @param parent Tree의 index
	 * @return Tree에서 parent의 right child index를 반환
	 */
	private int right(int parent) {
		return parent * 2 + 2;
	}

	/**
	 * @param child Tree의 index
	 * @return Tree에서 child의 parent index를 반환
	 */
	private int parent(int child) {
		return (child - 1) / 2;
	}

	/**
	 * Tree를 오름차순으로 정렬
	 */
	public void sort() {
		int tempTreeSize = treeSize;
		while (treeSize-- > 0) {
			elementSwap(0, treeSize);
			heapify(0);
		}
		treeSize = tempTreeSize;
	}

	@Override
	public String toString() {
		StringBuilder data = new StringBuilder();
		for (int index = 0; index < treeSize; index++)
			data.append((index + 1) + ".  " + tree[index].value + ", " + tree[index].subject + "\n");
		return data.toString();
	}
}