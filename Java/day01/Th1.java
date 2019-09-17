package day01;

// �ϳ��� task
class MyThread extends Thread{

	String name;
	
	public MyThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		// thread ������ �� �ȿ��� �Ͼ.
		for(int i=1; i<=1000; i++) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			yield(); // t3�� ���������� �����ϴ� �� ���� �ϱ� ���� �Լ�. �纸.
			System.out.println(name+" : "+i);
		}
	}
}

public class Th1 {

	public static void main(String[] args) {
		
		// �ΰ��� ������� �ΰ��� �۾��� �����ϴ� ���
		MyThread t1 = new MyThread("T1");
		MyThread t2 = new MyThread("T2");
		MyThread t3 = new MyThread("T3");
		
		t1.setPriority(1);
		t2.setPriority(2);
		t3.setPriority(10);
		
		t1.start(); // t1�� run�� ���۵�
		t2.start(); // t2�� run�� ���۵�
		t3.start();
	}
}
