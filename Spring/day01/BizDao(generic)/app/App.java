package com.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.user.User;

public class App {

	// IoC 구현 -> 다형성 구현 -> 
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		System.out.println("Spring Started");
		
		Biz<String, User> biz = (Biz<String, User>)factory.getBean("ubiz");
		
		User user = new User("id01", "pwd01", "who");
		
		try {
			biz.insert(user);
			System.out.println("Inserted OK from app");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		factory.close();
	}

}
