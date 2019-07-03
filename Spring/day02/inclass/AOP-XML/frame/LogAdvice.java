package com.frame;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class LogAdvice {
	public void printLog() {
		Date d = new Date();
		System.out.println(d+"[공통로그]비즈니스 로직 수행...");
	}
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object [] args = jp.getArgs();
		System.out.println(method+" : "+args[0]);
		System.out.println("[before]비즈니스 로직 수행...");
	}
	public void afterLog(JoinPoint jp, Object returnObj) {
		
		System.out.println("[after]비즈니스 로직 수행...");
		System.out.println("Log Result : "+returnObj);
	}
	
	public void exLog(JoinPoint jp, Exception ex) {
		Date d = new Date();
		System.out.println(d+"[EX로그]Exception...");
		System.out.println(ex.getMessage());
		
	}
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		
		stopWatch.start();
		System.out.println("Before Action...");
		Object obj = pjp.proceed();
		System.out.println("After Action...");
		stopWatch.stop();
		
		System.out.println("Processing Time : "+stopWatch.getTotalTimeSeconds());
		return obj;
	}
	
}
