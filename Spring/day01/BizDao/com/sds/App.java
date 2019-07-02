package com.sds;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		System.out.println("Spring Started");
		
		// UserOracleDao
		Biz biz1 = (Biz)factory.getBean("ub1"); // ub1과 연결된 bean을 biz1에 부르기.
		biz1.register(); // biz의 register를 실행하면 userbiz의 register가 dao의 insert를 실행
		
		// UserMariaDao
		Biz biz2 = (Biz)factory.getBean("ub2"); 
		biz2.register();
		
	}

}
