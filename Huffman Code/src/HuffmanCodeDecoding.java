import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Enumeration;

/**
 * Huffman Decoding 
 * 알고리즘 00반 201201356 김민호
 * @author KMH
 */
public class HuffmanCodeDecoding {

	private static String data;
	private static Node huffmanTree = new Node(null, null);
	private static Hashtable<String, Character> huffmanCode = new Hashtable<String, Character>();
	private static StringBuilder decodingData = new StringBuilder();

	public static void main(String[] args) throws IOException {
		input();
		buildHuffmanTree();
		decoding();
		output();
	}

	private static void buildHuffmanTree() {
		for (Enumeration<String> key = huffmanCode.keys(); key.hasMoreElements();) {
			Node tree = huffmanTree;
			String code = key.nextElement();

			for (int index = 0, size = code.length(); index < size; index++) {
				if (code.charAt(index) == '0') {
					if (tree.left == null)
						tree.left = new Node(null, null);
					tree = tree.left;
				} else {
					if (tree.right == null)
						tree.right = new Node(null, null);
					tree = tree.right;
				}
			}
			tree.letter = huffmanCode.get(code);
		}
	}

	private static void decoding() {
		Node tree = huffmanTree;

		for (int index = 0, size = data.length(); index < size; index++) {
			char bit = data.charAt(index);
			if (bit == '0')
				tree = tree.left;
			if (bit == '1')
				tree = tree.right;
			if (tree.left == null || tree.right == null) {
				decodingData.append(tree.letter);
				tree = huffmanTree;
			}
		}
	}

	private static void input() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data10_encoded.txt"));
		data = reader.readLine();
		reader.close();

		reader = new BufferedReader(new FileReader("data10_table.txt"));
		String line = null;
		while ((line = reader.readLine()) != null)
			huffmanCode.put(line.split(",")[1], line.charAt(0));
		reader.close();
	}

	private static void output() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("hw08_00_201201356_decoded.txt"));
		writer.write(decodingData.toString());
		writer.close();
	}

	private static class Node {
		public char letter;
		public Node left;
		public Node right;

		public Node(Node left, Node right) {
			this.left = left;
			this.right = right;
		}
	}
}