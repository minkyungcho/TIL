package springtv;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		System.out.println("Spring Start ...");
		
		AbstractApplicationContext factory = 
		new GenericXmlApplicationContext("mytv.xml"); // xml보고 spring container 생성됨, 일반 java class가 돈다. 
		// 요청 받으면 constructor 생성
		
		
		System.out.println("Spring Started");
		
		TV stv = (TV)factory.getBean("stv");
		stv.turnOn();
		stv.volumeUp(10);
		System.out.println(stv);
		
		
		factory.close();
	}

}
