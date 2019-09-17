package day01;

// 하나의 task
class MyThread2 implements Runnable{

	String name;
	
	
	
	public MyThread2(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		// thread 행위는 이 안에서 일어남.
		for(int i=1; i<=30; i++) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name+" : "+i);
		}
	}
	
}

public class Th2 {

	public static void main(String[] args) {
		
		// interface에서 상속 받았을 경우에 객체 생성 방법
		// thread 객체 생성하고 그 안에 interface에서 상속받은 thread 넣기.
		Thread t1 = new Thread(new MyThread2("T1"));
		Thread t2 = new Thread(new MyThread2("T2"));
		t1.start();
		t2.start();
		
	}

}
