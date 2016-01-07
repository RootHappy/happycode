package com.wang.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject{
	private List<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;

	public WeatherData() {
		observers = new ArrayList<>();
	}

	@Override
	public void registerObservers(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObservers(Observer o) {
		int i = observers.indexOf(o);
		if(i >= 0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for(Observer observer : observers) {
			observer.update(temperature, humidity, pressure);
		}
	}

	public void measurementChanged() {
		notifyObservers();
	}

	public void setMeasurements(float temperature , float humidity , float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementChanged();
	}

}
