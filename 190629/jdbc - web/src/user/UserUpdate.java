package com.user;

import com.frame.Biz;
import com.vo.User;

public class UserUpdate {

	public static void main(String[] args) {
		User user = new User("id01","pwd01", "김민석");
		Biz<String, User> biz = new UserBiz();
		try {
			biz.modify(user);
			System.out.println("UPDATE OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
