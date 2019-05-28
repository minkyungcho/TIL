package set;

import java.util.HashSet;
import java.util.Random;
//중복 불가, 순서 없음

public class SetTest {

	public static void main(String[] args) {
//		HashSet<Object> set = new HashSet<>(); // 여러 타입이 들어 갈 수 있음.
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
				break; // set 갯수가 6개면 break
			}
		}
		System.out.println(set.toString());
	}

}
