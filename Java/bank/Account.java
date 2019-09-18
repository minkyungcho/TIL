package bank;

public class Account {
	private int balance;
	
	public Account() {
		
	}
	
	public Account(int balance) {
		this.balance = balance;
	}
	
	public void deposit(int money) throws Exception{
		if(money <= 0) {
			throw new Exception();
		}
		balance += money;
	}
	
	public void withdraw(int money) throws Exception{
//		if(money >= balance) {
//			throw new Exception();
//		}
		balance -= money;
	}
	
	public int getBalance() {
		return balance;
	}
	
	@Override
	public String toString() {
		return "Account [balance]";
	}
	
}
