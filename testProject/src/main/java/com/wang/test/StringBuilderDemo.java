package com.wang.test;

public class StringBuilderDemo {

	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder();
		System.out.println(builder.append("wangyunlong"));
		System.out.println(builder.append("zhangsan"));
		System.out.println(builder.capacity());
		System.out.println(builder.charAt(4));
		System.out.println(builder.codePointAt(4));
		System.out.println(builder.delete(0, 10));
		System.out.println(builder.deleteCharAt(2));
		System.out.println(builder.hashCode());
		System.out.println(builder.indexOf("n"));
		System.out.println(builder.insert(0, true));
		System.out.println(builder.length());
		System.out.println(builder.lastIndexOf("an"));
		System.out.println(builder.getClass());
	}

}
