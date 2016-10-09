import java.util.Scanner;

/**
 * �ð��� �Է� �޾� ��ħ�� ��ħ ������ ������ ����ϴ� ���α׷� 
 * �˰��� 00�� 201201356 ���ȣ
 * @author Kim Min-Ho
 */
public class ClockAngle {

	private static final String LINE = "======================\n";
	private static final String GUIDE = "�ð��� �Է��ϼ���: �� ��\n";

	public static void main(String... args) {
		Scanner input = new Scanner(System.in);
		double hour, minute;

		while (true) {
			printForm();
			hour = input.nextDouble();
			minute = input.nextDouble();
			if (checkTime(hour, minute))
				continue;
			System.out.format("%.1f��\n", calculateAngle(hour, minute));
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
			System.out.println("�߸��� �ð��� �Է�");
			return true;
		}
		if (minute < 0 || minute >= 60) {
			System.out.println("�߸��� �а� �Է�");
			return true;
		}
		return false;
	}

	private static void printForm() {
		System.out.print(LINE + GUIDE);
	}
}