package day02;

import java.util.Scanner;

class Sus implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println("-");
			// thread�� name�� id ���
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
		Thread t1 = null; // �����忡 �̸� "s1" ����
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
