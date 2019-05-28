package ws;

import java.util.ArrayList;

public class UserDB extends DB<User, String> {
	@Override
	public void Create(User obj) {
		System.out.println(obj.getId()+" "+obj.getName()+" "+obj.getPwd()+" Created...");
	}

	@Override
	public User Select(String obj) {
		User user = null;
		System.out.println("Search:"+obj);
		user = new User("id02", "Tom", "pwd02");
		return user;
	}
	
	@Override
	public ArrayList<User> Select() {
		
		User u1 = new User("id001", "SEO", "SEO01");
		User u2 = new User("id002", "LIM", "LIM01");
		User u3 = new User("id003", "KIM", "KIM01");
		User u4 = new User("id004", "CHO", "CHO01");
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		userList.add(u4);
		
		return userList;
	}
	
	@Override
	public void Update(User obj) {
		System.out.println(obj.getId()+" Updated...");		
	}

	@Override
	public void Delete(User obj) {
		System.out.println(obj.getId()+" Deleted...");
	}

}
