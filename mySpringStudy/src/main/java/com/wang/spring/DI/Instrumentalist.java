package com.wang.spring.DI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("kenny")
public class Instrumentalist implements Performer{

	public Instrumentalist() {
	}

	@Override
	public void perform() {
		System.out.println("Playing " + song + " : ");
		intrument.play();
	}

	@Value("#{systemEnvironment['HOME']}")
	private String song;

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	//通过byType自动装配，可省略setter方法，局限：只能有一个合适的bean
	//可以配置自动装配是可选的	required=false
	@Autowired(required = false)
	@Qualifier("piano")
	private Instrument intrument;
}
