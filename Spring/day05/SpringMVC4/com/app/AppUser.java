package com.app;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.vo.User;

public class AppUser {

	public static void main(String[] args) {
		AbstractApplicationContext factory
		= new GenericXmlApplicationContext("myspring.xml");
		System.out.println("Spring Started");
		
		Biz<String, User> ubiz = 
				(Biz<String, User>) factory.getBean("ubiz");
		ArrayList<User> list = null;
//		String str = "id01";
//		User user = new User("id01", "pwd01", "user01");
//		User userS = new User();
		
		
		// UPDATE
//		try {
//			ubiz.modify(user);
//			System.out.println("UPDATE OK");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		// DELETE
//		try {
//			ubiz.remove(str);
//			System.out.println("DELETE OK");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// SELECT
//		try {
//			userS = ubiz.get(str);
//			System.out.println(userS);
//			System.out.println("SELECT OK");
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		// SELECTALL
		try {
			list = ubiz.get();
			for(User u:list) {
				System.out.println(u);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		// INSERT
//		User userI = new User("id17", "pwd17", "¿ÃΩ√»∆");
//		try {
//			ubiz.register(userI);
//			System.out.println("Inserted Ok From app.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		factory.close();
	}

}
