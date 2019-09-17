package day01;

public class Th3 {

	public static void main(String[] args) {
		
		// class 만들지 않고 thread 만들기.
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=100; i++) {
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("R1:"+i);
				}
			}
		};
		Runnable r2 = new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=100; i++) {
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("R2:"+i);
				}
			}
		};
		ThreadGroup tg1 = new ThreadGroup("TG1"); // thread 그룹 생성
		// 하나의 그룹에 두개의 쓰레드 묶기
		tg1.setMaxPriority(3);
		new Thread(tg1,r1,"th1").start(); // threadgroup에 "th1" 이름을 가진 thread r1을 집어 넣는다.
		new Thread(tg1,r2,"th1").start();
		
	}

}
