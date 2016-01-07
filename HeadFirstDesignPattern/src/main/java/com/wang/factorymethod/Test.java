package com.wang.factorymethod;

public class Test {
	public static void main(String[] args) {
		PizzaStore nyStore =new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoStylePizzaStore();
		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza.getName() + "\n");
		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("Joel ordered a " + pizza.getName() + "\n");
	}
}
