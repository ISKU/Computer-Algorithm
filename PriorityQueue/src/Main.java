import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static PriorityQueue pQueue;

	public static void main(String... args) throws IOException {
		Scanner input = new Scanner(System.in);
		pQueue = new PriorityQueue(readData());
		viewScreen(Sentence.GUIDE);

		while (true) {
			String header = null;

			switch (input.nextInt()) {
			case 1:
				System.out.print("신규 작업명: ");
				String subject = input.next();
				System.out.print("우선순위(0~999)");
				pQueue.insert(new Node(input.nextInt(), subject));
				header = Sentence.GUIDE;
				break;
			case 2:
				Node maxNode = pQueue.getMax();
				header = "**** MAX: " + maxNode.value + ", " + maxNode.subject + " ****\n\n";
				break;
			case 3:
				pQueue.extract_max();
				header = Sentence.COMPLETE;
				break;
			case 4:
				System.out.print("수정할 노드의 index: ");
				int index = input.nextInt();
				System.out.print("수정할 노드의 value: ");
				pQueue.increaseKey(index, input.nextInt());
				header = Sentence.COMPLETE;
				break;
			case 5:
				System.out.print("삭제할 노드의 Index: ");
				pQueue.delete(input.nextInt());
				header = Sentence.COMPLETE;
				break;
			case 6:
				System.exit(0);
			default:
				continue;
			}

			clearScreen();
			viewScreen(header);
		}
	}

	private static void viewScreen(String header) {
		System.out.print(header);
		System.out.print(pQueue.toString());
		System.out.print(Sentence.LINE);
		System.out.print(Sentence.TASK);
		System.out.print(Sentence.LINE);
	}

	private static void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

	private static Node[] readData() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data03.txt"));
		String line = null;

		ArrayList<Node> data = new ArrayList<Node>();
		while ((line = reader.readLine()) != null) {
			String[] currentData = line.split(", ");
			data.add(new Node(Integer.parseInt(currentData[0]), currentData[1]));
		}
		reader.close();

		Node[] dataArray = new Node[data.size()];
		for (int index = 0, length = data.size(); index < length; index++)
			dataArray[index] = data.get(index);
		data = null;

		return dataArray;
	}
}