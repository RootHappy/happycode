package com.wang.observer;

public class Test {
	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		new CurrentConditionsDisplay(weatherData);//观察者
		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}

}
