package com.user;

import com.frame.Biz;
import com.vo.User;

public class UserInsert {

	public static void main(String[] args) {

		User u = new User("id01", "pwd01", "user1");
		Biz<String, User> biz = new UserBiz();
		
		try {
			biz.register(u);
			System.out.println("INSERT OK");
		} catch (Exception e) {
			e.printStackTrace();
	    }
	}

}
