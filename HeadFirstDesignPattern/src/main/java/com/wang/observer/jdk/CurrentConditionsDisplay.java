package com.wang.observer.jdk;

import java.util.Observable;
import java.util.Observer;

import com.wang.observer.DisplayElement;

public class CurrentConditionsDisplay implements Observer , DisplayElement{
	private Observable observable;
	private float temperature;
	private float humidity;

	public CurrentConditionsDisplay(Observable observable ) {
		this.observable = observable;
		this.observable.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Current conditions: " + temperature + "F degrees and " +humidity + " % humidity");
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof WeatherData) {
			WeatherData weatherData = (WeatherData)o;
			this.temperature = weatherData.getTemperature();	//拉数据
			this.humidity = weatherData.getHumidity();				//拉数据
			display();
		}
	}

}
