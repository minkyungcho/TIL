package set;

import java.util.HashMap;
// 어떤 특정 정보를 map에 저장했다가 가져오기

public class MapTest {

	public static void main(String[] args) {
		HashMap<Integer, Product> map = new HashMap<>();
		
		map.put(111, new Product(111, "T-shirt", 10000)); // 저장
		map.put(222, new Product(222, "T-shirt", 10000));
		map.put(333, new Product(333, "T-shirt", 10000));
		map.put(444, new Product(444, "T-shirt", 10000));
		map.put(555, new Product(555, "T-shirt", 10000));
		
		System.out.println(map.size());
		
		Product p = map.get(222); // 가져오기
		System.out.println(p);
	}

}
