package day01;

public class Variable2 {

	public static void main(String[] args) {
		boolean b = true;
		char c = '한'; // a;
		byte by = 127;
		short sh = 10;
		int i1 = 1200000000; // default
		int i2 = 1200000000;
		long ll = 10;
//		long ll = 22000000000000L; // 이 숫자는 default가 long 이다. 
		by = (byte)(20 + 10);
		
//		sh = (short) (300000 + i);
		ll = i1 + i2; // int형의 max값은 20억인데 초과함. => (-)값 나옴
//		ll = (long)(i1 + i2); // 윗줄과 똑같은 값 나옴. 먼저 계산된 값이 정수형이기 때문.  
		ll = (long)i1 + (long)i2;
		System.out.println(ll);
		
//		float f = 1.0;
		float f = 1.0F; 
		double d = 1.0; // default. 실수는 기본값이 double이다. float에 넣을 수 없음.
		
//		float ff = 1.0 + 1.0;
		float ff = 1.0F + 1.0F;
		
		final int a = 10; // a는 변할 수 없는 상수.
//		a = 100; // 될 수 없음.
		
		
	}

}
