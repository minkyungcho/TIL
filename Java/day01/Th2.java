package day01;

// �ϳ��� task
class MyThread2 implements Runnable{

	String name;
	
	
	
	public MyThread2(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		// thread ������ �� �ȿ��� �Ͼ.
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
		
		// interface���� ��� �޾��� ��쿡 ��ü ���� ���
		// thread ��ü �����ϰ� �� �ȿ� interface���� ��ӹ��� thread �ֱ�.
		Thread t1 = new Thread(new MyThread2("T1"));
		Thread t2 = new Thread(new MyThread2("T2"));
		t1.start();
		t2.start();
		
	}

}
