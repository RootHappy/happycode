package com.wang.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * java.lang.ThreadLocal类的内容
 * 2015-10-19 10:23
 * */
public class ThreadLocalDemo {

	public static  void main(String[] args) {
		ThreadLocal<Integer> ids = new ThreadLocal<Integer>();

		System.out.println(ids.get());

		System.out.println(ThreadId.get());
	}

	static class ThreadId {
		private static final AtomicInteger nextId = new AtomicInteger(0);

		private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
			@Override
			protected Integer initialValue() {
				return nextId.getAndIncrement();	//nextId++
			}
		};

		public static int get() {
			return threadId.get();
		}

	}

}
