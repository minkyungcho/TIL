package com.user;

import com.frame.Biz;
import com.vo.User;

public class UserDelete {

	public static void main(String[] args) {
		String str = "id59";
		Biz<String, User> biz = new UserBiz();
		try {
			biz.remove(str);
			System.out.println("DELETE OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
