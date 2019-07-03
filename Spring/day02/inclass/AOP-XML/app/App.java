package com.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.product.Product;
import com.user.User;

public class App {

	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		System.out.println("Spring Started");
		
		Biz<String, User> ubiz = (Biz<String, User>)factory.getBean("ubiz");
		Biz<Integer, Product> pbiz = (Biz<Integer, Product>)factory.getBean("pbiz");
		
		User user = null;
		try {
			user = ubiz.select("id00");
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Product product = new Product(101, "p01", 1000.0);
//		
//		try {
//			pbiz.insert(product);
//			System.out.println("Product Inserted OK from app");
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	
		
//		User user = new User("id01", "pwd01", "who");
//		try {
//			ubiz.insert(user);
//			System.out.println("User Inserted OK from app");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		factory.close();
	}

}
