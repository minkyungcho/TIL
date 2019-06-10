package test;

import com.UserBiz;

import frame.Biz;
import vo.User;

public class UserUpdate {

	public static void main(String[] args) {
		User user = new User("id37","pwd326", "¹Î¼®¹Î°æ");
		Biz<String, User> biz = new UserBiz();
		try {
			biz.modify(user);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
