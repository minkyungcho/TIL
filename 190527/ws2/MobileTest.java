package ws2;

public class MobileTest {

	public static void main(String[] args) {
		// ������ Mobile ��ü ����
        // ������ ��ü�� ���� ���
        // ������ Mobile ��ü�� 10�о� ���� 
        // 10�� ���� �� ��ü ���� ���
        // ������ Mobile ��ü�� 5�о� ��ȭ
        // 5�� ��ȭ �� ��ü ���� ���
		Mobile mo[] = new Mobile[2];
		mo[0] = new Ltab("Ltab", 500, "AP-01");
		mo[1] = new Otab("Otab", 1000, "AND-20");
		System.out.println("\tMobile\t\tBattery\t\tOS");
		System.out.println("---------------------------------------------------------");
		for(Mobile mob:mo) {
			System.out.println(mob.toString());
		}
		System.out.println("10�� ����");
		System.out.println("\tMobile\t\tBattery\t\tOS");
		System.out.println("---------------------------------------------------------");
		for(Mobile mob:mo) {
			System.out.println(mob.toString());
		}
		System.out.println("5�� ��ȭ");
		System.out.println("\tMobile\t\tBattery\t\tOS");
		System.out.println("---------------------------------------------------------");
		for(Mobile mob:mo) {
			System.out.println(mob.toString());
		}
	
	}


}
