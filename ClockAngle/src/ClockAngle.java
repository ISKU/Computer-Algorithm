import java.util.Scanner;

/**
 * 시간을 입력 받아 시침과 분침 사이의 각도를 계산하는 프로그램 
 * 알고리즘 00반 201201356 김민호
 * @author Kim Min-Ho
 */
public class ClockAngle {

	private static final String LINE = "======================\n";
	private static final String GUIDE = "시간을 입력하세요: 시 분\n";

	public static void main(String... args) {
		Scanner input = new Scanner(System.in);
		double hour, minute;

		while (true) {
			printForm();
			hour = input.nextDouble();
			minute = input.nextDouble();
			if (checkTime(hour, minute))
				continue;
			System.out.format("%.1f도\n", calculateAngle(hour, minute));
		}
	}

	private static double calculateAngle(double hour, double minute) {
		double angle = (hour < minute) 
				? angleOfMinute(minute) - angleOfHour(hour, minute)
				: angleOfHour(hour, minute) - angleOfMinute(minute);

		return Math.abs(angle);
	}

	private static double angleOfHour(double hour, double minute) {
		return (hour * 30) + (minute * 0.5);
	}

	private static double angleOfMinute(double minute) {
		return minute * 6;
	}

	private static boolean checkTime(double hour, double minute) {
		if (hour < 0 || hour > 12) {
			System.out.println("잘못된 시간값 입력");
			return true;
		}
		if (minute < 0 || minute >= 60) {
			System.out.println("잘못된 분값 입력");
			return true;
		}
		return false;
	}

	private static void printForm() {
		System.out.print(LINE + GUIDE);
	}
}