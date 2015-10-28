package com.wang.dubbo.demo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Provider {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"provider.xml"});
		context.start();
		System.in.read();
//		com.alibaba.dubbo.container.Main.main(args);
	}
}
