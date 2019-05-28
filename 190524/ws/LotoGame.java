package ws;

import java.util.Scanner;

public class LotoGame {

	public static void main(String[] args) throws Exception {
//		시나리오
//		1. 유저는 한개의 계좌를 가지고 있다.
//		2. 유저는 계좌의 남은 돈이 허락하는 한도 내에서 복권을 살 수 있다.
//		3. 유저는 복권을 살 때 자동으로 번호를 생성할지, 수동으로 원하는 숫자를 입력할지 선택할 수 있다.
//		3-1. 수동으로 할 시 원하는 숫자 6개를 입력한다.
//		4. 복권은 입력받은 숫자와 당첨 숫자, 등수에 따른 상금을 보유한다.
//		5. 복권은 입력받은 숫자와 당첨 숫자를 비교해서 등수를 계산한다.
//		6. 복권은 등수에 따른 상금을 계좌에 입금한다.
		User u = new User();
		
		while(true) {
			System.out.print("계좌에 얼마를 충전하시겠습니까? > ");
			Scanner sc = new Scanner(System.in);
			int money = sc.nextInt();
			u.deposit(money);
			System.out.print("복권을 몇개 사시겠습니까? > ");
			int ticketNum = sc.nextInt();
			u.buyTicket(ticketNum);
			
		}
//		System.out.print("계좌에 얼마를 충전하시겠습니까? > ");
//		Scanner sc = new Scanner(System.in);
//		User u = new User(sc.nextInt());
//		int depositMoney = sc.nextInt();
//		u.deposit(depositMoney);
//		System.out.println(u.remainMoney);
		
		
	}

}
