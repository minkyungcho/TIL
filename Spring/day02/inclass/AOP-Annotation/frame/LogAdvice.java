package com.frame;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.StopWatch;

public class LogAdvice {
	public void printLog() {
		Date d = new Date();
		System.out.println(d+"[공통로그]비즈니스 로직 수행...");
	}
	
	// beforeLog는 ~패키지의~함수의~가실행될때 나타난다.
	@Before("execution(* com..Biz+.insert(..)")
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object [] args = jp.getArgs();
		System.out.println(method+" : "+args[0]);
		System.out.println("[before]비즈니스 로직 수행...");
	}
	
	
	@AfterReturning(
			pointcut = "execution(* com..Biz+.select(..)",
			returning = "returnObj"
			)
	public void afterLog(JoinPoint jp, Object returnObj) {
		
		System.out.println("[after]비즈니스 로직 수행...");
		System.out.println("Log Result : "+returnObj);
	}
	
	@AfterThrowing(
			pointcut = "execution(* com..Biz+.select(..)",
			throwing = "ex"
			)
	public void exLog(JoinPoint jp, Exception ex) {
		Date d = new Date();
		System.out.println(d+"[EX로그]Exception...");
		System.out.println(ex.getMessage());
		
	}
	
	@Around("execution(* com..Biz+.select(..)")
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
