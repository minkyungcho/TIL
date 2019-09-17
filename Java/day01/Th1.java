package day01;

// 하나의 task
class MyThread extends Thread{

	String name;
	
	public MyThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		// thread 행위는 이 안에서 일어남.
		for(int i=1; i<=1000; i++) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			yield(); // t3가 연속적으로 동작하는 것 방지 하기 위한 함수. 양보.
			System.out.println(name+" : "+i);
		}
	}
}

public class Th1 {

	public static void main(String[] args) {
		
		// 두개의 쓰레드로 두개의 작업을 수행하는 경우
		MyThread t1 = new MyThread("T1");
		MyThread t2 = new MyThread("T2");
		MyThread t3 = new MyThread("T3");
		
		t1.setPriority(1);
		t2.setPriority(2);
		t3.setPriority(10);
		
		t1.start(); // t1의 run이 동작됨
		t2.start(); // t2의 run이 동작됨
		t3.start();
	}
}
