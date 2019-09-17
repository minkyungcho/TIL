package day01;

class SaveThread extends Thread{
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			save();
		}
	}
	public void save() {
		System.out.println("SAVE..");
	}
}

public class Th4 {

	public static void main(String[] args) {
		SaveThread st = new SaveThread();
		st.setDaemon(true); // main �Լ� ������ ���Ƽ� �����ִ� thread ���̴� ���
		st.start();
		for(int i=1; i<=20; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}

}
