import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Hashtable;
import java.util.Enumeration;

/**
 * Huffman Encoding 
 * 알고리즘 00반 201201356 김민호
 * @author KMH
 */
public class HuffmanCodeEncoding {

	private static String data;
	private static Node huffmanTree;
	private static Hashtable<Character, Integer> frequencies = new Hashtable<Character, Integer>();
	private static Hashtable<Character, String> huffmanCode = new Hashtable<Character, String>();
	private static PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
	private static StringBuilder encodingData = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data10.txt"));
		data = reader.readLine();
		reader.close();

		frequencyScanning();
		buildHuffmanTree();
		createHuffmanCode(huffmanTree, "");
		encoding();
		output();
	}

	private static void frequencyScanning() {
		for (int index = 0, size = data.length(); index < size; index++) {
			char letter = data.charAt(index);
			frequencies.put(letter, frequencies.containsKey(letter) ? frequencies.get(letter) + 1 : 1);
		}

		for (Enumeration<Character> key = frequencies.keys(); key.hasMoreElements();) {
			char letter = key.nextElement();
			pQueue.add(new Node(letter, frequencies.get(letter)));
		}
	}

	private static void buildHuffmanTree() {
		while (pQueue.size() != 1) {
			Node node = new Node(pQueue.poll(), pQueue.poll());
			node.frequency = node.left.frequency + node.right.frequency;
			pQueue.add(node);
		}

		huffmanTree = pQueue.peek();
	}

	private static void createHuffmanCode(Node parent, String code) {
		if (parent.left == null || parent.right == null) {
			huffmanCode.put(parent.letter, code);
			return;
		}

		createHuffmanCode(parent.left, code + "0");
		createHuffmanCode(parent.right, code + "1");
	}

	private static void encoding() {
		for (int index = 0, size = data.length(); index < size; index++)
			encodingData.append(huffmanCode.get(data.charAt(index)));
	}

	private static void output() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("hw08_00_201201356_encoded.txt"));
		writer.write(encodingData.toString());
		writer.close();

		writer = new BufferedWriter(new FileWriter("hw08_00_201201356_table.txt"));
		writer.write(huffmanCode.containsKey(' ') ? " ," + huffmanCode.get(' ') + "\n" : "");
		for (char letter = 97; letter <= 122; letter++)
			writer.write(huffmanCode.containsKey(letter) ? letter + "," + huffmanCode.get(letter) + "\n" : "");
		writer.close();
	}

	private static class Node implements Comparable<Node> {
		public char letter;
		public int frequency;
		public Node left;
		public Node right;

		public Node(char letter, int frequency) {
			this.letter = letter;
			this.frequency = frequency;
		}

		public Node(Node left, Node right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public int compareTo(Node compare) {
			if (this.frequency < compare.frequency)
				return -1;
			else if (this.frequency > compare.frequency)
				return 1;
			else
				return 0;
		}
	}
}