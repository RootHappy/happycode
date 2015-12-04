package com.wang.java.lang;

public class ComparableDemo implements Comparable<ComparableDemo>{
	private int count = 0;


	public static void main(String[] args) {
		ComparableDemo comparableDemo1 = new ComparableDemo();
		ComparableDemo comparableDemo2 = new ComparableDemo();
		System.out.println(comparableDemo1.compareTo(comparableDemo2));
		comparableDemo2.count = 2;
		System.out.println(comparableDemo1.compareTo(comparableDemo2));
		comparableDemo1.count = 3;
		System.out.println(comparableDemo1.compareTo(comparableDemo2));
	}

	@Override
	public int compareTo(ComparableDemo o) {
		return this.count  - o.count;
	}

}
