package com.wang.java.lang;

public class EnumDemo {
	enum Factory {
		FACTORY1,
		FACTORY2;
	}

	public static void main(String[] args) {
		System.out.println(Factory.FACTORY1.name());
		System.out.println(Factory.FACTORY1.ordinal());
		System.out.println(Factory.FACTORY1.toString());
		System.out.println(Factory.FACTORY1.hashCode());
		System.out.println(Factory.valueOf(Factory.class,"FACTORY2"));
	}

}
