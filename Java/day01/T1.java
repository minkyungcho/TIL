package day01;

public class T1 {

	public static void main(String[] args) throws InterruptedException {
		// �Ϲ� ���� ���μ����� ����Ǵ� ���� 
		// A �� ������ B ���۵�.
		for(int i=1; i<=20; i++) {
			Thread.sleep(10);
			System.out.println("A : "+i);
		}
		System.out.println("end");
		for(int i=1; i<=20; i++) {
			Thread.sleep(10);
			System.out.println("B : "+i);
		}
		System.out.println("end");
	}

}
