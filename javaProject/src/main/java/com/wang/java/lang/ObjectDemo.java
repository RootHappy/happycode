package com.wang.java.lang;

public class ObjectDemo {

	public static void main(String[] args) {
		Object object = new Object();
		//返回Class对象
		System.out.println(object.getClass());
		//equals()
		System.out.println(object.equals(object));
		System.out.println(object.equals(null));

		//hashCode()——内存地址
		//1.在同一个应用中，多次调用保证一致
		//2.使用equals()方法返回相等后，每个对象的hashCode方法必须返回相同的整数
		System.out.println(object.hashCode());

		System.out.println(object.toString());
	}

}
