package com.user;

import com.frame.Biz;
import com.vo.User;

public class UserUpdate {

	public static void main(String[] args) {
		User user = new User("id37","pwd326", "말숙씨");
		Biz<String, User> biz = new UserBiz();
		try {
			biz.modify(user);
			System.out.println("UPDATE OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
