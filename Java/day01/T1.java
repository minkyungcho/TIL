package day01;

public class T1 {

	public static void main(String[] args) throws InterruptedException {
		// 일반 적인 프로세스가 진행되는 과정 
		// A 다 끝나고 B 시작됨.
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
