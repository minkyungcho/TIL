package test;

import com.UserBiz;

import frame.Biz;
import vo.User;

public class UserSelectAll {

	public static void main(String[] args) {
		
		Biz<String, User> biz = new UserBiz();
		try {
			biz.get();
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
