package day01;

import java.util.Scanner;

class NewThread extends Thread{
	int num;
	public NewThread(int num) {
		this.num = num;
	}
	@Override
	public void run() {
		for(int i=1; i<=num; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
}

public class Th5 {
	
	public static void main(String[] args) {
		System.out.println("Enter Input Num : ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		Thread t1 = new Thread(new NewThread(num));
		t1.start();
	}
}
