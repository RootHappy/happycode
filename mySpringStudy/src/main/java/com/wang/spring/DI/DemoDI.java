package com.wang.spring.DI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoDI {

	public static void main(String[] args) {
		ApplicationContext ctx =  new ClassPathXmlApplicationContext("spring/springDI.xml");
		Performer performer = (Performer)ctx.getBean("kenny");
		performer.perform();
	}

}
