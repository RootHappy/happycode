package com.wang.java.lang;

import java.net.URL;

public class ClassLoaderDemo {

	public static void main(String[] args) {
		ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();

		//使用系统类加载器加载类
		URL url = ClassLoader.getSystemResource("test.txt");
		System.out.println(url.getFile());
//		ClassLoader.getSystemResourceAsStream(name);
//		ClassLoader.getSystemResources(name);

		//使用当前类加载器加载资源
		URL url2  = classLoader.getResource("test.txt");
		System.out.println(url2.getFile());
		//使用Class对象加载，当前目录为当前类
		URL url3  = ClassLoaderDemo.class.getResource("test.txt");

		System.out.println(url3.getFile());
		System.out.println(ClassLoader.getSystemClassLoader());
		System.out.println(classLoader.toString());
		System.out.println(classLoader.getParent());
	}

}
