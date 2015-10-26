package com.wang.dubbo.demo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wang.dubbo.demo.DemoService;

public class Consumer {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
		context.start();
		DemoService demoService = (DemoService)context.getBean("demoService");
		System.out.println(demoService.sayHello("王云龙"));
	}

}
