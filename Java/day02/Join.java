package day02;

class Th2 extends Thread{
	
	int sum;
	
	public int getSum() {
		return sum;
	}
	
	public void run() {
		int i = 1;
		while(!isInterrupted()) {
			sum += i; // 1~10±îÁö µ¡¼À
			if(i == 10) {
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			i++;
		}
		System.out.println("Th2 END ...");
	}
}

public class Join {
	
	public static void main(String[] args) {
		Th2 th2 = new Th2();
		System.out.println("Start ...");
		th2.start(); 
		try {
			th2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("sum:"+th2.getSum());
	}
}
