package test;

import com.UserDao;

import frame.Dao;
import vo.User;

public class UserSelect {

	public static void main(String[] args) {
		Dao<String, User> dao = new UserDao();
		String str = "id01";
		try {
			dao.select(str);
			System.out.println("User Seleted");
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
