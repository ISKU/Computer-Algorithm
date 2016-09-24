import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * PriorityQueue Test Main Class
 * 
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class Main {

	private static PriorityQueue pQueue;

	/**
	 * 사용자는 아래 번호의 입력으로 PriorityQueue의 각 기능들을 테스트해 볼 수 있다.
	 * 1. 작업추가
	 * 2. 최대값
	 * 3. 최대 우선순위 작업 처리
	 * 4. 원소 키 값 증가
	 * 5. 작업 제거
	 * 6. 종료
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
			String header = Sentence.GUIDE;

			switch (Integer.parseInt(input.nextLine())) {
			case 1:
				System.out.print("신규 작업명: ");
				String subject = input.nextLine();
				System.out.print("우선순위(0~999): ");
				pQueue.insert(new Node(Integer.parseInt(input.nextLine()), subject));
				header = Sentence.ADD;
				break;
			case 2:
				if (pQueue.isEmpty())
					header = Sentence.EMPTY;
				else {
					Node maxNode = pQueue.max();
					header = "**** MAX: " + maxNode.value + ", " + maxNode.subject + " ****\n\n";
				}
				break;
			case 3:
				if (pQueue.isEmpty())
					header = Sentence.EMPTY;
				else {
					pQueue.extractMax();
					header = Sentence.COMPLETE;
				}
				break;
			case 4:
				if (pQueue.isEmpty())
					header = Sentence.EMPTY;
				else {
					System.out.print("수정할 노드의 index: ");
					int index = Integer.parseInt(input.nextLine());
					System.out.print("수정할 노드의 value: ");
					pQueue.increaseKey(index, Integer.parseInt(input.nextLine()));
					header = Sentence.COMPLETE;
				}
				break;
			case 5:
				System.out.print("삭제할 노드의 Index: ");
				if (pQueue.isEmpty())
					header = Sentence.EMPTY;
				else {
					pQueue.delete(Integer.parseInt(input.nextLine()));
					header = Sentence.COMPLETE;
				}
				break;
			case 6:
				System.exit(0);
			default:
				break;
			}

			clearScreen();
			viewScreen(header);
		}
	}

	/**
	 * 현재 PriorityQueue의 원소들을 출력하는 메소드
	 * 사용자는 PriorityQueue의 작업 수행을 확인하는 용도로 사용한다.
	 * 
	 * @param header 사용자가 작업한 내용을 설명하는 문장
	 */
	private static void viewScreen(String header) {
		System.out.print(header);
		System.out.print(pQueue.toString());
		System.out.print(Sentence.LINE);
		System.out.print(Sentence.TASK);
		System.out.print(Sentence.LINE);
	}

	/**
	 * 화면에 출력된 모든 내용을 지우는 메소드
	 */
	private static void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

	/**
	 * 파일에서 PriorityQueue에 사용 될 원소들의 배열을 가져오는 메소드
	 * 각 원소는 Node Class로 정의한 배열을 사용하고 각 원소의 값은 아래와 같다.
	 * Node.value = Priority, Node.subject = 과목명
	 * 
	 * @return 파일로 부터 읽어들인 원소들을 배열로 저장하여 반환
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