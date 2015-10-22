package com.wang.java.RMI;

import java.rmi.Naming;

public class HelloRmiClient {

	public static void main(String[] args) {
		try {
			IHello hello = (IHello) Naming.lookup("rmi://localhost:1099/hello");
			System.out.println(hello.sayHello("wangyunlong"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
