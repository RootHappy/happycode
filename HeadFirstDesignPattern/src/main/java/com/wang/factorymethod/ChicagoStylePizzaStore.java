package com.wang.factorymethod;

public class ChicagoStylePizzaStore extends PizzaStore{

	@Override
	Pizza createPizza(String type) {
		if(type.equals("cheese")) {
			return new ChicagoStyleCheesePizza();
		}
		return null;
	}

}
