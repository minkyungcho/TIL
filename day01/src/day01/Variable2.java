package day01;

public class Variable2 {

	public static void main(String[] args) {
		boolean b = true;
		char c = '��'; // a;
		byte by = 127;
		short sh = 10;
		int i1 = 1200000000; // default
		int i2 = 1200000000;
		long ll = 10;
//		long ll = 22000000000000L; // �� ���ڴ� default�� long �̴�. 
		by = (byte)(20 + 10);
		
//		sh = (short) (300000 + i);
		ll = i1 + i2; // int���� max���� 20���ε� �ʰ���. => (-)�� ����
//		ll = (long)(i1 + i2); // ���ٰ� �Ȱ��� �� ����. ���� ���� ���� �������̱� ����.  
		ll = (long)i1 + (long)i2;
		System.out.println(ll);
		
//		float f = 1.0;
		float f = 1.0F; 
		double d = 1.0; // default. �Ǽ��� �⺻���� double�̴�. float�� ���� �� ����.
		
//		float ff = 1.0 + 1.0;
		float ff = 1.0F + 1.0F;
		
		final int a = 10; // a�� ���� �� ���� ���.
//		a = 100; // �� �� ����.
		
		
	}

}
