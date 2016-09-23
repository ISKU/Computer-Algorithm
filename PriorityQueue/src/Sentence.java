/**
 * PriorityQueue를 테스트할 때 사용자에게 보여줄 문장들을 미리 정의한 클래스
 * 
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class Sentence {
	
	/**
	 * GUIDE: PriorityQueue의 내역 설명
	 * ADD: PriorityQueue에 원소가 추가되었을 때의 설명
	 * COMPLETE: PriorityQueue의 작업을 성공적으로 성공했을 때의 설명
	 * LINE: line
	 * TASK: 사용자가 PriorityQueue의 각 기능을 테스트하기 하기 위해 이를 설명한 작업내역
	 */
	public static final String GUIDE = "**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다 ****\n\n";
	public static final String ADD = "**** 작업 추가 완료 ****\n\n";
	public static final String COMPLETE = "**** 한 개의 작업을 처리하였습니다. ****\n\n";
	public static final String LINE = "---------------------------------------\n";
	public static final String TASK = "1.작업추가\t 2.최대값\t 3.최대 우선순위 작업 처리\n4.원소 키 값 증가\t 5.작업 제거\t 6.종료\n";
}