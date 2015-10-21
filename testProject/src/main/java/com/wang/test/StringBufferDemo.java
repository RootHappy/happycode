package com.wang.test;

/**
 * java.lang.StringBuffer线程安全的类，与Java.lang.StringBuilder类唯一的区别是，大部分方法都使用了synchronized,
 * 导致在单线程情况下，性能提升不是很明显.
 * 方法类似与StringBuilferDemo
 * 2015-10-20 12:52
 * */
public class StringBufferDemo {
	private final static int conut = 10000000;

	public static void main(String[] args) {
		long start = 0L;
		long end = 0L;

		@SuppressWarnings("unused")
		String resultString = "";
		StringBuffer resultStringBuffer = new StringBuffer();
		StringBuilder resultStringBuilder = new StringBuilder();
		start = System.currentTimeMillis();
		for(int i = 0 ; i < 10000; i++) {
			resultString += displayString(i);
		}
		end = System.currentTimeMillis();
		System.out.println("String time : " + (end - start));

		start = System.currentTimeMillis();
		for(int i = 0 ; i < conut; i++) {
			resultStringBuffer.append(displayString(i));
		}
		end = System.currentTimeMillis();
		System.out.println("StringBuffer time : " + (end - start));

		start = System.currentTimeMillis();
		for(int i = 0 ; i < conut; i++) {
			resultStringBuilder.append(displayString(i));
		}
		end = System.currentTimeMillis();
		System.out.println("StringBuilder time : " + (end - start));


	}

	static String displayString(int src) {
		return src + " ";
	}

}
