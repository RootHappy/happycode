package com.wang.test;

import java.util.HashMap;

/**
 *java.lang.Runnable接口
 * */
public class MultiThreadDemo {
	/**
	 * 使用Runnable接口创建匿名类，来完成线程执行的任务
	 * java.lang.Runnable
	 * */
	public static void runnableTest() {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				System.out.println("Running task through Runnable Interface.");
			}
		};

		Thread thread = new Thread(task);
		System.out.println(thread.toString());
		thread.start();
	}

	/**
	 * java.lang.ThreadLocal<T> local; 线程局部变量 ThreadLocal.ThreadLocalMap threadLocals = null;
	 * 在线程中存在一个threadLocals，保存本线程的局部变量，当使用local.get()获取值时，
	 * 查找当前线程的map中是否有值，如果没有则调用initialValue()方法添加，有则直接返回
	 * */
	public static void threadLocalTest() {
		final ThreadLocal<Integer> local = new ThreadLocal<Integer>(){
			@Override
			protected Integer initialValue() {
				return 1;
			}
		};
		Thread[] threads = new Thread[5];

		for(int i = 0; i < 5; i++) {
			threads[i] = new Thread(new Runnable(){
				public void run() {
					System.out.println(local.get());
					local.set(20);
				}
			});
		}
		for(int i = 0; i < 5; i++) {
			threads[i].start();
		}
	}


	public static void main(String[] args) {
		runnableTest();
		threadLocalTest();
	}

}
