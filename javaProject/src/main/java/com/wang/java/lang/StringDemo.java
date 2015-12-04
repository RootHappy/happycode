package com.wang.java.lang;

public class StringDemo {

	public static void main(String[] args) {
		String value = new String("王云龙");

		System.out.println("String length() : " + value.length());
		System.out.println("String isEmpty() : " + value.isEmpty());
		System.out.println("String charAt() : " + value.charAt(1));
		System.out.println("String codePointAt() : " + value.codePointAt(1));
		System.out.println("String charAt() : " + value.charAt(1));
		System.out.println("String codePointBefore() : " + value.codePointBefore(2));
		System.out.println("String codePointBefore() : " + value.codePointCount(0, 2));
		System.out.println(value.concat("asdf"));
		System.out.println("abc".matches("^a.*"));

		StringBuilder sb = new StringBuilder();
		String str1 = "wang";
		String str = null;
		sb.append(str);
		sb.append(str1);
		System.out.println(sb.toString());
	}

}
