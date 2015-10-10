package com.wang.spring.DI;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("audience")
@Aspect
public class Audience {

	@Pointcut("execution(* com.wang.spring.DI.Performer.perform(..))")
	public void perform() {
	}

	@Before("perform()")
	public void takeSeats() {
		System.out.println("The audience is taking their seats.");
	}
	@Before("perform()")
	public void turnOffCellPhones() {
		System.out.println("The audience is turning off their cellphones.");
	}
	@AfterReturning("perform()")
	public void applaud() {
		System.out.println("CLAP CLAP CLAP CLAP CLAP");
	}
	@AfterThrowing("perform()")
	public void demandRefund() {
		System.out.println("Boo! We want our money back!");
	}

	//环绕通知
	@Around("perform()")
	public void watchPerformance(ProceedingJoinPoint joinpoint) {
		try {
			System.out.println("The audience is taking their seats.");
			System.out.println("The audience is turning off their cellphones.");
			long start = System.currentTimeMillis();
			joinpoint.proceed();
			long end = System.currentTimeMillis();
			System.out.println("CLAP CLAP CLAP CLAP CLAP");
			System.out.println("The performance took " + ( end - start ));
		}catch(Throwable t) {
			System.out.println("Boo! We want our money back!");
		}
	}

}
