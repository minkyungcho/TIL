package com.sds;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	// IoC 구현 -> 다형성 구현 -> 
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		System.out.println("Spring Started");
		
//		STV stv = (STV)factory.getBean("stv"); // 결합도 높음
//		stv.turnOn();

		TV tv = (TV)factory.getBean("stv"); 
		tv.turnOn();
		tv.up();
		System.out.println(tv);
		
//		tv.up(); // 불가능
		STV stv = (STV)tv;
		stv.up();
		
		TV tv2 = (TV)factory.getBean("ltv"); 
		tv2.up();
		System.out.println(tv2);
		
		
		TV tv3 = (TV)factory.getBean("ltv"); // 서로 같은 reference
		tv3.turnOn();
		
		if(tv == tv2) {
			System.out.println("ok");
		}
	}

}
