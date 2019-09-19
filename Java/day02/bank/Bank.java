package bank;

class Header extends Thread{
	Account acc;
	
	public Header(Account acc) {
		this.acc = acc;
	}
	
	public void run() {
		for(int i=0; i<100; i++) {
			int money = (int)(Math.random()*3+1)*80;
			try {
				Thread.sleep(500);
				acc.deposit(money);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Branch extends Thread{
	Account acc;
	String name;
	
	public Branch(String name, Account acc) {
		this.name = name;
		this.acc = acc;
	}
	
	public void run() {
		while(acc.getBalance() > 0) {
			int money = (int)(Math.random()*3+1)*100;
			try {
				Thread.sleep(100);
				acc.withdraw(money);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(name+" "+acc.getBalance());
		}
	}
}

public class Bank {
	
	public static void main(String[] args) {
		 Account acc = new Account(10000);
		 Header h = new Header(acc);
		 h.start();
		 
		 Branch b1 = new Branch("b1", acc);
		 Branch b2 = new Branch("b2", acc);
		 Branch b3 = new Branch("b3", acc);
		 Branch b4 = new Branch("b4", acc);
		 
		 b1.start();
		 b2.start();
		 b3.start();
		 b4.start();
		 
		 
	}

}
