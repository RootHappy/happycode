package com.wang.callback.boss;

public class Boss {
	private String name;

	public Boss(String name) {
		this.name = name;
	}

	public void getWorkerEvent(Worker worker , Event event) {
		System.out.println(name + "老板收到事件信息：" + worker.getName() + " : " + event.happendEvent());
	}

}
