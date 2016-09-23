/**
 * PriorityQueue에 사용 될 각 원소를 표현한 클래스
 * 
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class Node {

	public int value;
	public String subject;

	/**
	 * @param value 해당 원소의 Priority
	 * @param subject 해당 원소의 과목명
	 */
	public Node(int value, String subject) {
		this.value = value;
		this.subject = subject;
	}
}