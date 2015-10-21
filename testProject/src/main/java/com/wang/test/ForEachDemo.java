package com.wang.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/**
 * for-each循环结构简洁，预防bug,且没有性能损失
 * 三种不适用的情况：
 * 	1.过滤——遍历集合，并删除选定的元素，可以使用显示的迭代器，调用remove
 *  	2.转换——遍历列表或者数组，并取代它部分或者全部的元素值，就需要使用列表迭代器或者数组索引
 *  	3.平行迭代——并行得遍历多个集合，需要显示的控制迭代器或者索引变量，以便所有迭代器或者索引变量都可以同步前移
 *
 * */
public class ForEachDemo {

	enum Suit {
		CLUB,DIAMOND,HEART,SPADE
	}

	enum Rank {
		ACE,DEUCE,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,
		NINE,TEN,JACK,QUEEN,KING
	}

	static class Card {
		Suit suit;
		Rank rank;
		Card(Suit suit,Rank rank) {
			this.suit = suit;
			this.rank =rank;
		}
	}

	public static void threeSituation(){
	//1.
		List<String> lists = new ArrayList<String>();
		lists.add("A");
		lists.add("B");
		lists.add("C");
		lists.add("D");
		lists.add("E");
		lists.add("F");
		lists.add("G");
		//利用迭代器删除
		for(Iterator<String> i = lists.iterator(); i.hasNext();) {
			if(i.next().equals("B"))
				i.remove();
		}
		for(String item : lists) {
			//错误的删除方式
//			if(item.equals("B"))
//				lists.remove(item);
			System.out.println(item);
		}

	}

	public static void main(String[] args) {
		String[] arrs = {"wang","yun","long"};
		List<String> lists = new ArrayList<String>();
		lists.add("zhang");
		lists.add("san");
		//优先使用
		for(String str : arrs)
			System.out.println(str);
		//次之
		for(int i = 0,n = arrs.length; i < n; i++)
			System.out.println(arrs[i]);
		//再次之
		for(int i = 0; i < arrs.length; i++)
			System.out.println(arrs[i]);
		//集合迭代除for-each的替代
		for(Iterator<String> i = lists.iterator(); i.hasNext();)
			System.out.println(i.next());
		//尽量避免使用，暴露了局部变量
		Iterator<String> iterator = lists.iterator();
		while(iterator.hasNext())
			System.out.println(iterator.next());

		Collection<Suit> suits = Arrays.asList(Suit.values());
		Collection<Rank> ranks = Arrays.asList(Rank.values());

		List<Card> deck = new ArrayList<Card>();
		//优先使用
		for(Suit suit : suits)
			for(Rank rank : ranks)
				deck.add(new Card(suit,rank));
		//次之
		for(Iterator<Suit> i = suits.iterator(); i.hasNext();) {
			Suit suit = i.next();
			for(Iterator<Rank> j = ranks.iterator(); j.hasNext();)
				deck.add(new Card(suit,j.next()));
		}

		threeSituation();
		//错误的迭代器
//		for(Iterator<Suit> i = suits.iterator(); i.hasNext();)
//			for(Iterator<Rank> j = ranks.iterator(); j.hasNext();)
//				deck.add(new Card(i.next(),j.next()));
	}

}
