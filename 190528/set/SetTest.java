package set;

import java.util.HashSet;
import java.util.Random;
//�ߺ� �Ұ�, ���� ����

public class SetTest {

	public static void main(String[] args) {
//		HashSet<Object> set = new HashSet<>(); // ���� Ÿ���� ��� �� �� ����.
		HashSet<Integer> set = new HashSet<>(); 
//		set.add(1);
//		set.add(2);
//		set.add(3);
//		set.add(2);
//		set.add(3);
		Random r = new Random();
		while(true){
			set.add(r.nextInt(45)+1);
			if(set.size() == 6) {
				break; // set ������ 6���� break
			}
		}
		System.out.println(set.toString());
	}

}
