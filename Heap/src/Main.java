import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String... args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data03.txt"));
		String line = null;
		
		ArrayList<Node> data = new ArrayList<Node>();
		while ((line = reader.readLine()) != null) {
			String[] currentData = line.split(", ");
			data.add(new Node(Integer.parseInt(currentData[0]), currentData[1]));
		}
		
		Node[] dataArray = new Node[data.size()];
		for (int index = 0, length = data.size(); index < length; index++)
			dataArray[index] = data.get(index);
		data = null;
		
		MaxHeap maxHeap = new MaxHeap();
		maxHeap.buildMaxHeap(dataArray);
		System.out.println(maxHeap.toString());
	}
}
