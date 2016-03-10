package com.wang.somequestion;

/**
 * 递归求幂运算
 * 
 * @author wangyunlong
 *
 */
public class Pow {
	
	/**
	 * 求幂运算 x^n
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public static long pow(int x, int n) {
		if( n == 0) {
			return 1;
		}
		if(n == 1){
			return x;
		}
		
		if( n % 2 == 0) {
			return pow(x * x , n / 2);
		}else{
			return pow(x * x , n / 2) * x;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(pow(2,0));
	}

}
