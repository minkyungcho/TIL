package day02;

import java.util.Scanner;

class Sus implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println("-");
			// thread의 name과 id 출력
//			System.out.println(
//					Thread.currentThread().getName()
//					+" "+
//					Thread.currentThread().getId()
//					);
		}
	}
}

public class Suspend {
	
	public static void main(String[] args) {
		Thread t1 = null; // 쓰레드에 이름 "s1" 지정
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("input cmd ? ");
			int cmd = sc.nextInt();
			if(cmd == 1) {
				t1 = new Thread(new Sus(), "s1");
				t1.start();
			}else if(cmd == 2) {
				t1.suspend(); // duplicated
				System.out.println("Suspended");
			}else if(cmd == 3) {
				t1.resume();
			}else if(cmd == 4) {
				t1.stop();
			}else if(cmd == 9) {
				return;
			}
		}
	}
}
