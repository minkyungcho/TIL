package com.app;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.vo.Product;
import com.vo.User;

public class AppProduct {

	public static void main(String[] args) {
		AbstractApplicationContext factory
		= new GenericXmlApplicationContext("myspring.xml");
		System.out.println("Spring Started");
		
//		Biz<String, User> ubiz = 
//				(Biz<String,User>) factory.getBean("ubiz");
		
		Biz<Integer, Product> pbiz = 
				(Biz<Integer, Product>) factory.getBean("pbiz");
		
	
//		
		ArrayList<Product> list = null;
		int i = 104;
		Product productS = new Product();
		
		// UPDATE
//		Product productU = new Product(101, "사과", 1000.0, "apple.jpg")x;
//		try {
//			pbiz.modify(productU);
//			System.out.println("UPDATE OK");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		// DELETE
//		try {
//			pbiz.remove(i);
//			System.out.println("DELETE OK");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// SELECT
//		try {
//			productS = pbiz.get(i);
//			System.out.println(productS);
//			System.out.println("SELECT OK");
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		// SELECTALL
		try {
			list = pbiz.get();
			for(Product p:list) {
				System.out.println(p);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		// INSERT

//		Product productI = new Product("오리들", 20000.0, "ducks.jpg");
//		try {
//			pbiz.register(productI);
//			System.out.println("Inserted Ok From app.");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		factory.close();
	}

}
