package ws;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class User {
	private Ticket[] ticket;
	private int remainMoney;
	private Account account;
	
	public User() {
		this.account = new Account();
		this.remainMoney = 0;
//		this.ticket[0] = new Ticket();
	}
	public User(int money) {

	}
	public void deposit(int money) {
	
		account.deposit(money);
	}
	public void buyTicket(int numOfTicket) throws Exception {
		if(numOfTicket*1000 > account.getMoney()) {
			
			System.out.println("�ܰ� �����մϴ�. �� �����ϼ���.");
			return;
		}
		
		ticket = new Ticket[numOfTicket];
		for(int i=0; i<numOfTicket; i++) {
			if(setAuto()) {
				ticket[i] = new Ticket();
			}else {
				int my_Number[] = new int[6];
				Scanner sc = new Scanner(System.in);
				System.out.print("6�� ���ڸ� �Է��Ͻÿ�. > ");
				for(int j=0; j<my_Number.length; j++) {
					my_Number[j] = sc.nextInt();
				}
				ticket[i] = new Ticket(my_Number);
//				System.out.println(Arrays.toString(ticket));
			}
		}
		for(int i=0; i<numOfTicket; i++) {
			System.out.println(ticket[i].toString());	
		}
		this.remainMoney = account.getMoney();
//		u.remainMoney -= numOfTicket*1000;
		System.out.println("�ܰ�� "+remainMoney+"�� �Դϴ�.");
//		return ticket;
	}
	
	public boolean setAuto() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("�ڵ�(1), ����(2) > ");
		int A = sc.nextInt();
		if(A==1) {
			return true;
		}else if(A==2) {
			return false;
		}else {
			throw new Exception();
		}
	}
	public int[] setTicketNum() {
		return null;
	}
	public int getRemainMoney() {
		return remainMoney;
	}
	
}
