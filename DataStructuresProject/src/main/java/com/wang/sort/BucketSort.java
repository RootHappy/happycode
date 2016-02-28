package com.wang.sort;
/**
 * 桶式排序
 * 要求：输入数据A[1],A[2]...A[N]必须只由小于M的正整数组成。
 * 思路：使用一个大小为M称为Count数组，全初始化为0.当读入A[i]时，Count[ A[i] 加一
 * @author wangyunlong
 *
 */
public class BucketSort {
	
	/**
	 * 桶式排序
	 * 
	 * @param data 输入数据
	 * @param length 数据长度
	 * @param maxValue 最大正整数
	 */
	public static void bucketSort(int[] data , int length, int maxValue){
		int[] count = new int[maxValue + 1];
		//初始化为0
		for(int i = 0; i < maxValue+1; i++) {
			count[i] = 0;
		}
		for(int i = 0;i < length;i++) {
			count[data[i]] += 1;
		}
		for(int i = 0; i < maxValue +1; i++	) {
			for(int j = 0; j < count[i];j++){
				System.out.print(i + " ");
				
			}
		}
	}

	public static void main(String[] args) {
		int[] data = {1,3,2,1,5,3,2,1,5,6};
		BucketSort.bucketSort(data, data.length, 7);
		
	}

}
