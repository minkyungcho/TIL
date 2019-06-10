package test;

import com.UserBiz;

import frame.Biz;
import vo.User;

public class UserDelete {

	public static void main(String[] args) {
		String str = "id67";
		Biz<String, User> biz = new UserBiz();
		try {
			biz.remove(str);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
