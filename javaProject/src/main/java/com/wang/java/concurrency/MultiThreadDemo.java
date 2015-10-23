package com.wang.java.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 *java.lang.Runnable接口
 *java.lang.ThreadLocal类
 *java.util.concurrent.Callable接口
 *java.util.concurrent.Executor接口
 *java.util.concurrent.ExecutorService接口
 *
 * */
public class MultiThreadDemo {
	/**
	 * 使用Runnable接口创建匿名类，来完成线程执行的任务
	 * Runnable不返回任何值，同时也不抛出checked Exception
	 * Callable可返回值或者抛出异常
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
			Random random = new Random();
			@Override
			protected Integer initialValue() {
				return random.nextInt(100);
			}
		};
		Thread[] threads = new Thread[5];

		for(int i = 0; i < 5; i++) {
			threads[i] = new Thread(new Runnable(){
				public void run() {
					System.out.println(local.get());
				}
			});
		}
		for(int i = 0; i < 5; i++) {
			threads[i].start();
		}
	}

	public static void callableTest() {
		Callable<String> task = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("Callable is invoked");
				return "wangyunlong";
			}
		};
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<String> result = exec.submit(task);
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void executorTest() {
		Runnable task = new Runnable() {
			public void run() {
				System.out.println("wangyunlong");
			}
		};
		Executor executor = new Executor() {
			@Override
			public void execute(Runnable command) {
				command.run();
			}
		};
		Executor executorAsyn = new Executor() {
			@Override
			public void execute(Runnable command) {
				new Thread(command).start();
			}
		};
		//在当前线程执行，不严格要求异步执行
		executor.execute(task);
		//异步执行task的Executor
		executorAsyn.execute(task);
	}

	public static void scheduledExecutorServiceTest() {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable beeper = new Runnable(){
			public void run() {
				System.out.println("beep");
			}
		};
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 1,TimeUnit.SECONDS);
		scheduler.schedule(new Runnable(){
			public void run() {
				beeperHandle.cancel(true);
			}
		}, 1 * 2, TimeUnit.SECONDS);
	}


	public static void main(String[] args) {
//		runnableTest();
//		threadLocalTest();
//		callableTest();
//		executorTest();
		scheduledExecutorServiceTest();
	}

}
