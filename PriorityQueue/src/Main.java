import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * PriorityQueue Test Main Class
 * 
 * �˰��� 00�� 201201356 ���ȣ
 * @author Kim Min-Ho
 */
public class Main {

	private static PriorityQueue pQueue;

	/**
	 * ����ڴ� �Ʒ� ��ȣ�� �Է����� PriorityQueue��  �� ��ɵ��� �׽�Ʈ�� �� �� �ִ�.
	 * 1. �۾��߰�
	 * 2. �ִ밪
	 * 3. �ִ� �켱���� �۾� ó��
	 * 4. ���� Ű �� ����
	 * 5. �۾� ����
	 * 6. ����
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String... args) throws IOException {
		Scanner input = new Scanner(System.in);
		pQueue = new PriorityQueue(readData());
		clearScreen();
		viewScreen(Sentence.GUIDE);

		while (true) {
			String header = null;

			switch (input.nextInt()) {
			case 1:
				System.out.print("�ű� �۾���: ");
				String subject = input.next();
				System.out.print("�켱����(0~999)");
				pQueue.insert(new Node(input.nextInt(), subject));
				header = Sentence.ADD;
				break;
			case 2:
				Node maxNode = pQueue.max();
				header = "**** MAX: " + maxNode.value + ", " + maxNode.subject + " ****\n\n";
				break;
			case 3:
				pQueue.extractMax();
				header = Sentence.COMPLETE;
				break;
			case 4:
				System.out.print("������ ����� index: ");
				int index = input.nextInt();
				System.out.print("������ ����� value: ");
				pQueue.increaseKey(index, input.nextInt());
				header = Sentence.COMPLETE;
				break;
			case 5:
				System.out.print("������ ����� Index: ");
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

	/**
	 * ���� PriorityQueue�� ���ҵ��� ����ϴ� �޼ҵ�
	 * ����ڴ� PriorityQueue�� �۾� ������ Ȯ���ϴ� �뵵�� ����Ѵ�.
	 * 
	 * @param header ����ڰ� �۾��� ������ �����ϴ� ����
	 */
	private static void viewScreen(String header) {
		System.out.print(header);
		System.out.print(pQueue.toString());
		System.out.print(Sentence.LINE);
		System.out.print(Sentence.TASK);
		System.out.print(Sentence.LINE);
	}

	/**
	 * ȭ�鿡 ��µ� ��� ������ ����� �޼ҵ�
	 */
	private static void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

	/**
	 * ���Ͽ��� PriorityQueue�� ��� �� ���ҵ��� �迭�� �������� �޼ҵ�
	 * �� ���Ҵ� Node Class�� ������ �迭�� ����ϰ� �� ������ ���� �Ʒ��� ����.
	 * Node.value = Priority, Node.subject = �����
	 * 
	 * @return ���Ϸ� ���� �о���� ���ҵ��� �迭�� �����Ͽ� ��ȯ
	 * @throws IOException
	 */
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