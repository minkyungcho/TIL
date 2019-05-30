package test;

//import java.util.ArrayList;

import com.UserDao;

import frame.Dao;
import vo.User;

public class UserSelectAll {

	public static void main(String[] args) {
		Dao<String, User> dao = new UserDao();
		try {
			dao.select();
			System.out.println("User Seleted All");
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
