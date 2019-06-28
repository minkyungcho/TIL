package com.user;

import com.frame.Biz;
import com.vo.User;

public class UserInsert {

	public static void main(String[] args) {

		User u = new User("id03", "pwd03", "박서준");
		Biz<String, User> biz = new UserBiz();
		
		try {
			biz.register(u);
			System.out.println("INSERT OK");
		} catch (Exception e) {
			e.printStackTrace();
	    }
	}

}
