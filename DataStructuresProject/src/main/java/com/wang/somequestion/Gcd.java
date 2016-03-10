package com.wang.somequestion;
/**
 * 求最大公因数
 * 
 * 利用最大公约数，求解最小公倍数
 * 
 * @author wangyunlong
 *
 */
public class Gcd {
	
	/**
	 * 使用欧几里德算法，计算最大公因数
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static int greatestCommmonDivisor(int m , int n) {
		int rem;
		while (n > 0) {
			rem = m % n;
			m = n;
			n = rem;
		}
		return m;
	}
	
	/**
	 * 首先求解两个数的最大公约数，然后使用 m * n / gcd
	 * @param m
	 * @param n
	 * @return
	 */
	public static int leastCommonMultiple(int m , int n){
		int temp = greatestCommmonDivisor(m,n);
		return m * n / temp;
	}
	
	public static void main(String[] args) {
		System.out.println(greatestCommmonDivisor(50, 25));
		System.out.println(leastCommonMultiple(50,25));
	}

}
