package com.wang.callback.boss;

public class Test {

	public static void main(String[] args) {
		Boss boss = new Boss("老板");
		Worker worker1 = new Worker("张三" , boss);
		Worker worker2 = new Worker("李四" , boss);

		Event event1 = new EventA();
		Event event2 = new EventB();
		worker1.setEvent(event1);
		worker2.setEvent(event2);

		worker1.doWork();
		worker2.doWork();
	}

}
