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
			System.out.println("음수를 입금할순없습니다.");
		}
		else {
			this.money += money; 
		}
		
		
		System.out.println("충전금액은 "+money+"원, 잔고는 "+this.money+"원 입니다.");
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
