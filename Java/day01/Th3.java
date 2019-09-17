package day01;

public class Th3 {

	public static void main(String[] args) {
		
		// class ������ �ʰ� thread �����.
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
		ThreadGroup tg1 = new ThreadGroup("TG1"); // thread �׷� ����
		// �ϳ��� �׷쿡 �ΰ��� ������ ����
		tg1.setMaxPriority(3);
		new Thread(tg1,r1,"th1").start(); // threadgroup�� "th1" �̸��� ���� thread r1�� ���� �ִ´�.
		new Thread(tg1,r2,"th1").start();
		
	}

}
