package com.wang.sort;

import com.wang.tools.DisplayData;

/**
 * 插入排序
 * 2016-02-22
 * @author wangyunlong
 * */
public class InsertionSort {
	
	public static void main(String[] args) {
		int[] data = {2,5,-1,0,32,12,100,-100,34,-5,25};
		System.out.println("Before the data is sorted.");
		DisplayData.display(data);
		insertionSortAsc(data, data.length);
		DisplayData.display(data);
		insertionSortDesc(data,data.length);
		System.out.println("After the data is sorted.");
		DisplayData.display(data);
	}
	
	/**
	 * 插入排序算法实现：（升序）
	 * 插入排序思想：插入排序由N-1趟排序组成，对于P=1到P=N-1，插入排序保证
	 * 				0到P位置上的元素为已排序状态。
	 * 时间复杂度：O(N2)
	 * @param data 带排序整数数组
	 * @param length 数组长度(合法)
	 * */
	public static void insertionSortAsc(int[] data, int length){
		int temp;
		int j;
		for( int p = 1; p < length; p++) {
			temp = data[p];
			for(j = p; j > 0 && data[j - 1] > temp; j--) {
				data[j] = data[j - 1];
			}
			data[j] = temp;
		}
	}
	
	/**
	 * 插入排序算法实现：（降序）
	 * 插入排序思想：插入排序由N-1趟排序组成，对于P=1到P=N-1，插入排序保证
	 * 				0到P位置上的元素为已排序状态。
	 * 时间复杂度：O(N2)
	 * @param data 带排序整数数组
	 * @param length 数组长度(合法)
	 * */
	public static void insertionSortDesc(int[] data , int length){
		int temp , j;
		for(int p = 1; p < length; p++ ) {
			temp = data[p];
			for(j = p;j > 0; j--) {
				if(data[j - 1] < temp) {
					data[j] = data[j -1];
				}else{
					break;
				}
			}
			data[j] = temp;
		}
	}
}
