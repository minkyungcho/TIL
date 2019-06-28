package com.user;

import com.frame.Biz;
import com.vo.User;

public class UserSelectAll {

	public static void main(String[] args) {
		
		Biz<String, User> biz = new UserBiz();
		try {
			System.out.println("SELECT ALL OK");
			biz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
