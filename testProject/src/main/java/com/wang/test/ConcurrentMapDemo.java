package com.wang.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentMapDemo {

	public static void main(String[] args) throws InterruptedException {
		ConcurrentMap<String,String>  map = new ConcurrentHashMap<String, String>();
		System.out.println(map.putIfAbsent("a", "a"));
		System.out.println(map.get("a"));
		System.out.println(map.putIfAbsent("a", "b"));
		System.out.println(map.get("a"));
		ExecutorService exe = Executors.newCachedThreadPool();
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("wang");
			}
		};
		System.out.println(time(exe,5,runnable));
	}

	public static long time(Executor executor , int concurrency , final Runnable action) throws InterruptedException {
		final CountDownLatch ready = new CountDownLatch(concurrency);
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch done = new CountDownLatch(concurrency);
		for(int i = 0; i < concurrency; i++) {
			executor.execute(new Runnable() {
				public void run() {
					ready.countDown();
					try {
						start.await();
						action.run();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}finally {
						done.countDown();
					}
				}
			});
		}
		ready.await();
		long startNanos = System.nanoTime();
		start.countDown();
		done.await();
		return System.nanoTime() - startNanos;
	}

}
