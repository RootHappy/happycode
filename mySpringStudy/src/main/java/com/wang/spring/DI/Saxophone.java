package com.wang.spring.DI;

import org.springframework.stereotype.Component;

@Component("instrument")
public class Saxophone implements Instrument{
	public Saxophone() {
	}
	@Override
	public void play() {
		System.out.println("TOOT TOOT TOOT");
	}

}
