package com.wang.java.lang;

public class CharSequenceDemo implements Cloneable{

	public static void main(String[] args) {
		CharSequence c = new String("wangyunlong");
		System.out.println(c.length());
		System.out.println(c.charAt(2));
		System.out.println(c.toString());
		System.out.println(c.subSequence(2, 5));
	}

}
