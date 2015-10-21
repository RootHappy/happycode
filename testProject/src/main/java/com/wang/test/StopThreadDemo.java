package com.wang.test;

import java.util.concurrent.TimeUnit;

public class StopThreadDemo {

	private static volatile boolean stopRequested = false;

//	private static boolean stopRequested;
//
//	private static synchronized void requestStop() {
//		stopRequested = true;
//	}
//
//	private static synchronized boolean stopRequest(){
//		return stopRequested;
//	}
	public static  void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			public void run() {
				int i = 0;
//				while(!stopRequest())
				while(!stopRequested)
					i++;
			}
		});
		backgroundThread.start();
		TimeUnit.SECONDS.sleep(1);
//		requestStop();
		stopRequested = true;
	}

}
