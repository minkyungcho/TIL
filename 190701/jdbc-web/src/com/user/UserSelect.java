package com.user;

import com.frame.Biz;
import com.vo.User;

public class UserSelect {
	
	public static void main(String[] args) {
		String str = "id37";
		Biz<String, User> biz = new UserBiz();
		try {
			System.out.println("SELECT OK");
			biz.get(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
