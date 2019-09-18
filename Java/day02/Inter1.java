package day02;

class Th1 extends Thread{
	public void run() {
		while(!isInterrupted()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			System.out.println("Th1");
		}
		System.out.println("Th1 END ...");
	}
}

public class Inter1 {
	
	public static void main(String[] args) {
		Th1 th1 = new Th1();
		th1.start(); // ∏ÿ√Á¿÷¡ˆ æ ¥¬¥Ÿ.
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		th1.interrupt();
	}
}
