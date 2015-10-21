package com.wang.test;

import java.math.BigDecimal;

/**
 * 当需要精确计算，尤其是货币计算时，应该使用int long bigDecimal
 * */
public class FLoatAndDoubleDemo {

	public static void main(String[] args) {
		System.out.println(1.03-.42);
		System.out.println(1.00 - 9 * .10);
		moneyDouble();
		moneyBigDecimal();
		moneyLong();
	}

	/**
	 * 提升单位，元变为分(看数值的大小，选取int long)
	 * */
	static void moneyLong() {
		int itemsBought = 0;
		long funds = 100L;
		for(int price = 10; funds >= price; price += 10) {
			itemsBought++;
			funds -= price;
		}
		System.out.println(itemsBought + " items bought.");
		System.out.println("Money left over: " + funds + " cents");
	}

	/**
	 * 不存在精度问题
	 * 类型运算不方便，很慢
	 * */
	static void moneyBigDecimal() {
		BigDecimal TEN_CENTs = new BigDecimal(".10");
		int itemsBought = 0;
		BigDecimal funds = new BigDecimal("1.00");
		for(BigDecimal price = TEN_CENTs; funds.compareTo(price) >= 0; price = price.add(TEN_CENTs)) {
			itemsBought ++;
			funds = funds.subtract(price);
		}
		System.out.println(itemsBought + " items bought.");
		System.out.println("Money left over: $" + funds);
	}

	/**
	 * 存在精度问题
	 * */
	static void moneyDouble() {
		double funds = 1.00;
		int itemBought = 0;
		for(double price = .10;funds >= price;price +=.10) {
			funds -= price;
			itemBought++;
		}
		System.out.println(itemBought + " items bought.");
		System.out.println("Change: $" + funds);
	}

}
