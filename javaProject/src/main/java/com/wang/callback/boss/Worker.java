package com.wang.callback.boss;

import java.util.concurrent.TimeUnit;

public class Worker {
	private Event event;
	private String name;
	private Boss boss;

	public Worker(String name , Boss boss) {
		this.name = name;
		this.boss = boss;
	}

	public void doWork() {
		System.out.println(name + " is doing working ....");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + " was finished work.");
		boss.getWorkerEvent(this,event);
	}

	public Event getEvent() {
		return event;
	}

	public String getName() {
		return name;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setName(String name) {
		this.name = name;
	}
}
