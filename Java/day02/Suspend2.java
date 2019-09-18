package day02;

import java.util.Scanner;

class Sus2 implements Runnable{
	
	boolean spd = false; // suspend resume
	boolean stp = false; // suspend stop
	
	public void setStop() {
		stp = true;
	}
	public void setSus() {
		stp = true;
	}
	public void setRes() {
		stp = false;
	}
	
	
	@Override
	public void run() {
		while(!stp) {
			if(!spd) {
				// suspend가 아니면 실행 되어야함.
				System.out.println("-");
			}
			
		}
	}
}

public class Suspend2 {
	
	public static void main(String[] args) {
		Thread t1 = null; // 쓰레드에 이름 "s1" 지정
		Sus2 sus2 = null;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("input cmd ? ");
			int cmd = sc.nextInt();
			if(cmd == 1) {
				sus2 = new Sus2();
				t1 = new Thread(sus2, "s2");
				t1.start();
			}else if(cmd == 2) {
				sus2.setSus();
				System.out.println("Suspended");
			}else if(cmd == 3) {
				sus2.setRes();
			}else if(cmd == 4) {
				sus2.setStop();
			}else if(cmd == 9) {
				return;
			}
		}
	}
}
