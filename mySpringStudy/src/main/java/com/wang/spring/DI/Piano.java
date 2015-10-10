package com.wang.spring.DI;

import org.springframework.stereotype.Component;

@Component
public class Piano implements Instrument{
	public Piano() {
	}
	@Override
	public void play() {
		System.out.println("PLINK PLINK PLINK");
	}

}
