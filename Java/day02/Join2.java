package day02;

class Th3 extends Thread {
	int sum;
	int s, e;

	public Th3(int s, int e) {
		this.s = s;
		this.e = e;
	}

	public int getSum() {
		return sum;
	}

	@Override
	public void run() {

		if (s == 1) {
			while (!isInterrupted()) {
				sum += s;
				if (s == 100) {
					return;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
				s++;
			}
		} else if (s == 101) {
			while (!isInterrupted()) {
				sum += s;
				if (s == 150) {
					return;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
				s++;
			}
		}
	}
}

public class Join2 {

	public static void main(String[] args) {
		Th3 t1 = new Th3(1, 100);
		Th3 t2 = new Th3(101, 150);

		System.out.println("Start ...");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int tsum = t1.getSum() + t2.getSum();
		System.out.println("Total Sum : " + tsum);
	}

}
