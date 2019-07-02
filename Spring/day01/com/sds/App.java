package com.sds;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	// IoC ���� -> ������ ���� -> 
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		System.out.println("Spring Started");
		
//		STV stv = (STV)factory.getBean("stv"); // ���յ� ����
//		stv.turnOn();

		TV tv = (TV)factory.getBean("stv"); 
		tv.turnOn();
		tv.up();
		System.out.println(tv);
		
//		tv.up(); // �Ұ���
		STV stv = (STV)tv;
		stv.up();
		
		TV tv2 = (TV)factory.getBean("ltv"); 
		tv2.up();
		System.out.println(tv2);
		
		
		TV tv3 = (TV)factory.getBean("ltv"); // ���� ���� reference
		tv3.turnOn();
		
		if(tv == tv2) {
			System.out.println("ok");
		}
	}

}
