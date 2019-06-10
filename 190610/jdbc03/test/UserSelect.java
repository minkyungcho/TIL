package test;

import com.UserBiz;

import frame.Biz;
import vo.User;

public class UserSelect {
	
	public static void main(String[] args) {
		String str = "id69";
		Biz<String, User> biz = new UserBiz();
		try {
			biz.get(str);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
