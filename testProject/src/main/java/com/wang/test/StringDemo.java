package com.wang.test;

import java.io.UnsupportedEncodingException;

public class StringDemo {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String valueE = "wangyunlong";
		String valueC = "王云龙";
		char[] dest = new char[valueE.length()];

		System.out.println("length：" +valueE.length());
		System.out.println("length：" +valueC.length());
		System.out.println("is Empty：" +valueE.isEmpty());
		System.out.println("CharAt : " + valueE.charAt(2));
		System.out.println("CharAt : " + valueC.charAt(2));
		System.out.println("Code Point At: " + valueE.codePointAt(2));
		System.out.println("Code Point At: " + valueC.codePointAt(2));
		System.out.println("Code Point Befort: " + valueE.codePointBefore(3));
		System.out.println("Code Point Befort: " + valueC.codePointBefore(3));
		System.out.println("Code Point Count Between 0 and valueE.length: " + valueE.codePointCount(0, valueE.length()));
		valueE.getChars(0, valueE.length(), dest, 0);
		System.out.println(dest);
		System.out.println(new String(valueC.getBytes()));
		System.out.println(valueE.equals(new String(dest)));
		System.out.println("startsWith" + valueE.startsWith("w"));
		System.out.println("endsWith:" + valueE.endsWith("g"));
		System.out.println("hashCode : " + valueE.hashCode());
		System.out.println("index Of : " + valueE.indexOf("c"));
		System.out.println("last index of: " + valueE.lastIndexOf("g"));
		System.out.println("subString: " + valueE.substring(4));
		System.out.println("concat: " + valueE.concat("zhangsan"));
		System.out.println("replace: " + valueE.replace('g','x'));
		System.out.println("matches: " + valueE.matches("wangyunlong"));
		System.out.println("Split: " + valueE.split("g").length);
		System.out.println("lower Case: " + valueE.toLowerCase());
		System.out.println("upper Case: " + valueE.toUpperCase());
		System.out.println("trim : " + valueE.trim());
		System.out.println("Char Array: " + valueE.toCharArray().toString());
		System.out.println(String.valueOf(true));
		//String Performance
		String result = "";
		long start = System.currentTimeMillis();
		for(int i = 0; i < 100000; i++) {
			result += displayN(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - start));
		System.out.println(result);
		StringBuilder result2 = new StringBuilder();
		long start2 = System.currentTimeMillis();
		for(int i = 0; i < 100000; i++) {
			result2.append(displayN(i));
		}
		long end2 = System.currentTimeMillis();
		System.out.println("time: " + (end2 - start2));
		System.out.println(result2);

	}

	public static String displayN(int n ) {
		return Integer.toString(n) + " ";
	}

}
