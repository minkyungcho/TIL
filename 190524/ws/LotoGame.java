package ws;

import java.util.Scanner;

public class LotoGame {

	public static void main(String[] args) throws Exception {
//		�ó�����
//		1. ������ �Ѱ��� ���¸� ������ �ִ�.
//		2. ������ ������ ���� ���� ����ϴ� �ѵ� ������ ������ �� �� �ִ�.
//		3. ������ ������ �� �� �ڵ����� ��ȣ�� ��������, �������� ���ϴ� ���ڸ� �Է����� ������ �� �ִ�.
//		3-1. �������� �� �� ���ϴ� ���� 6���� �Է��Ѵ�.
//		4. ������ �Է¹��� ���ڿ� ��÷ ����, ����� ���� ����� �����Ѵ�.
//		5. ������ �Է¹��� ���ڿ� ��÷ ���ڸ� ���ؼ� ����� ����Ѵ�.
//		6. ������ ����� ���� ����� ���¿� �Ա��Ѵ�.
		User u = new User();
		
		while(true) {
			System.out.print("���¿� �󸶸� �����Ͻðڽ��ϱ�? > ");
			Scanner sc = new Scanner(System.in);
			int money = sc.nextInt();
			u.deposit(money);
			System.out.print("������ � ��ðڽ��ϱ�? > ");
			int ticketNum = sc.nextInt();
			u.buyTicket(ticketNum);
			
		}
//		System.out.print("���¿� �󸶸� �����Ͻðڽ��ϱ�? > ");
//		Scanner sc = new Scanner(System.in);
//		User u = new User(sc.nextInt());
//		int depositMoney = sc.nextInt();
//		u.deposit(depositMoney);
//		System.out.println(u.remainMoney);
		
		
	}

}
