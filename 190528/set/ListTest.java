package set;

import java.util.ArrayList;
import java.util.Random;

public class ListTest {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		Random r = new Random();
		while(true){
			list.add(r.nextInt(45)+1);
			if(list.size() == 20) {
				break; // set °¹¼ö°¡ 6°³¸é break
			}
		}
//		System.out.println(list.toString());
		
		for(Integer i:list) {
			System.out.println(i);
		}
	}

}
