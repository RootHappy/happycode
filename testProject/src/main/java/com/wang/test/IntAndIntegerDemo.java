package com.wang.test;

import java.util.Comparator;

/**
 * int和Integer的区别之处
 * 	1.int只有值，Integer是引用
 *  	2.int值存储在堆栈中，更快；Integer存储在堆中
 *  	3.int只提供基本的值，Integer提供基本值外的null
 * */
public class IntAndIntegerDemo {
	static int i = 42;

	public static void main(String[] args) {

		/**
		 *  == 比较两个对象的同一性（是否是同一个类的同一个实例）
		 * */
		Comparator<Integer> naturalOrder = new Comparator<Integer>() {
			@Override
			public int compare(Integer first, Integer second) {
				int f = first;
				int s = second;
				return f < s ? -1 : (f > s ? 1: 0);
//				return first < second ? -1 : (first == second ? 0: 1);
			}
		};

		System.out.println(naturalOrder.compare(1, 2));
		System.out.println(naturalOrder.compare(2, 1));
		System.out.println(naturalOrder.compare(1, 1));
		System.out.println(naturalOrder.compare(new Integer(1),new Integer(2)));
		System.out.println(naturalOrder.compare(new Integer(1), new Integer(1)));
		System.out.println(naturalOrder.compare(new Integer(2), new Integer(1)));

		/**
		 * Integer i 初始化为i = null;
		 * 在执行i == 42操作时，i会自动拆箱，当值为null是抛出NullPointerException
		 * 修复方式：int i;
		 * */
		if (i == 42) {
			System.out.println("wang");
		}

		Long sum = 0L;
		long start = System.currentTimeMillis();
		for(long i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		long end = System.currentTimeMillis();
		System.out.println((end - start) + " " + sum);

		long sum1 = 0L;
		long start1 = System.currentTimeMillis();
		for(long i = 0; i < Integer.MAX_VALUE; i++) {
			sum1 += i;
		}
		long end1 = System.currentTimeMillis();
		System.out.println((end1 - start1) + " " + sum1);


	}
}
