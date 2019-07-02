package com.sds;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		System.out.println("Spring Started");
		
		// UserOracleDao
		Biz biz1 = (Biz)factory.getBean("ub1"); // ub1�� ����� bean�� biz1�� �θ���.
		biz1.register(); // biz�� register�� �����ϸ� userbiz�� register�� dao�� insert�� ����
		
		// UserMariaDao
		Biz biz2 = (Biz)factory.getBean("ub2"); 
		biz2.register();
		
	}

}
