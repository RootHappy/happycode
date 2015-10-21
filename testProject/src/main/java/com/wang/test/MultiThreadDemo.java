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
