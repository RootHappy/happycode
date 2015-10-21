package com.wang.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCode {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/springBean.xml");
		HelloSay hello = (HelloSay)context.getBean("Hello");
		hello.Hello();
		System.out.println("Spring coding....");

		Thread thread ;
	}

}
