package set;

import java.util.ArrayList;
// size�� ������ ���� ����. ������.
// type cast �� �ʿ� ����.

public class ListTest2 {

	public static void main(String[] args) {
//		ArrayList<Object> list = new ArrayList<>(); // arraylist�� �������� Ÿ�� ���� �� ����
		ArrayList<User> list = new ArrayList<>();
		list.add(new User("id01", "james", "pwd01"));
		list.add(new User("id02", "james", "pwd02"));
		list.add(new User("id03", "james", "pwd03"));
		list.add(new User("id04", "james", "pwd04"));
		list.add(new User("id05", "james", "pwd05"));
		
		System.out.println(list.size());
		list.remove(3);
		System.out.println(list.size());
		for(User u:list) {
			System.out.println(u);
		}
		
	}

}
