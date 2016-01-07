package com.wang.decorator;

public class Test {

	public static void main(String[] args) {
		Beverage beverage = new Espresso();
		display(beverage);
		Beverage beverage2 = new DarkRoast();
		beverage2 = new Mocha(beverage2);
		beverage2 = new Mocha(beverage2);
		beverage2 = new Whip(beverage2);
		display(beverage2);
		Beverage beverage3 = new HouseBlend();
		beverage3 = new Soy(beverage3);
		beverage3 = new Mocha(beverage3);
		beverage3 = new Whip(beverage3);
		display(beverage3);
	}

	private static void display(Beverage beverage)  {
		System.out.println(beverage.getDescription() + " $" + beverage.cost());
	}

}
