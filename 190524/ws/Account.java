package ws;

public class Account {
	private int money; 
	public Account() {
		this.money = 0;
	}
	public Account(int money) {
		
	}
	public void deposit(int money) {
		
		if(money <= 0 ) {
			System.out.println("������ �Ա��Ҽ������ϴ�.");
		}
		else {
			this.money += money; 
		}
		
		
		System.out.println("�����ݾ��� "+money+"��, �ܰ�� "+this.money+"�� �Դϴ�.");
	}
	public int getMoney() {
		return money;
	}	
	
//	public int withdraw(int money) {
		
//		account.getRemainMoney() += money;
//		
//		return 
//	}
	
}
