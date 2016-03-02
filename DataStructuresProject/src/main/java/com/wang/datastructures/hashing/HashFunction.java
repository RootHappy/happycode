package com.wang.datastructures.hashing;

/**
 * 一些hash函数
 * 
 * @author wangyunlong
 *
 */
public class HashFunction {
	
	/**
	 * key为 int 类型的散列
	 * 
	 * @param key
	 * @param tableSize
	 * @return
	 */
	public static int hashInt(int key , int tableSize){
		return key % tableSize;
	}
	
	/**
	 * key 为String类型
	 * 
	 * key的ASCII和 散列
	 * @param key
	 * @param tagleSize
	 * @return
	 */
	public static int hashStrASCII(String key, int tableSize) {
		int hashVal = 0;
		for(int i = 0; i < key.length(); i++) {
			char ch = key.charAt(i);
			hashVal += ch;
		}
		return hashVal % tableSize;
	}
	
	/**
	 * 使用n次多项式散列
	 * 
	 * @param key
	 * @param tableSize
	 * @return
	 */
	public static int hashStr(String key , int tableSize){
		int hashVal = 0;
		for(int i = 0; i < key.length(); i++) {
			hashVal = (hashVal << 5) + key.charAt(i);
		}
		return hashVal % tableSize;
	}
	
	/**
	 * 非Integer和String类型的hash
	 * 
	 * @param key
	 * @param tableSize
	 * @return
	 */
	public static int hashObject(Object key, int tableSize) {
		return key.hashCode() % tableSize;
	}
	
	public static void main(String[] args) {
		System.out.println(1^2);
		
//		int value = "wang".hashCode();
//		System.out.println(value);
//		int h = (value ^ value >>> 20 ^ value >>> 12);
//		System.out.println(h);
//		System.out.println(h ^ h >>> 7 ^ h >>> 4);
//		
//		h = value;
//		System.out.println(h ^ (h >>> 4) ^ (h >>> 7) ^ (h >>> 12) ^ (h >>> 16) ^ (h >>> 20) ^ (h >>> 24) ^ (h >>> 27));
//		
		
		
//		System.out.println(hashStr("wang",17));
//		
//		System.out.println((int)"a".charAt(0));
//		System.out.println(hashStrASCII("a",7));
//				
//		System.out.println(hashInt(14, 7));
	}
}
