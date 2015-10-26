package com.wang.dubbo.demo.provider;

import com.wang.dubbo.demo.DemoService;

public class DemoServiceImpl implements DemoService{
	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
