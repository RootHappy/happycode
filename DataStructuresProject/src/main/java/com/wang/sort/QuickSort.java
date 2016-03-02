package com.wang.sort;

import com.wang.tools.DisplayData;
import com.wang.tools.GenerateData;

/**
 * 快速排序
 * 
 * @author wangyunlong
 *
 */
public class QuickSort {
	
	private static final int DEFAULT_SIZE = 3;

	/**
	 * 快速排序启动程序
	 * 
	 * @param data
	 * @param length
	 */
	public static void quickSort(int[] data, int length){
		qSort(data,0,length - 1);
	}
	
	/**
	 * 快速排序递归核心，小于一定长度后，使用插入排序可以提高性能
	 * 
	 * @param data
	 * @param left
	 * @param right
	 */
	public static void qSort(int[] data, int left, int right){
		if(left + DEFAULT_SIZE <= right){
			int pivot = median3(data, left, right);
			int i = left;
			int j = right - 1;
			for(;;){
				while(data[++i] < pivot) {//找小于pivot的元素
				}
				while(data[--j] > pivot){	//找大于pivot的元素
				}
				if( i < j) {
					swap(data,i,j);
				}else
					break;
			}
			swap(data,i,right-1);
			qSort(data,left,i - 1);
			qSort(data,i + 1, right);
		}else{
			insertionSortAsc(data,left ,right - left + 1);
		}
	}
	
	/**
	 * 从指定位置开始，插入排序
	 * 
	 * @param data
	 * @param index
	 * @param length
	 */
	private static void insertionSortAsc(int[] data,int index , int length){
		int temp;
		int j;
		for( int p = 1; p < length; p++) {
			temp = data[index + p];
			for(j = index + p; j > index && data[j - 1] > temp; j--) {
				data[j] = data[j - 1];
			}
			data[j] = temp;
		}
	}
	
	/**
	 * 三数中值，选取枢纽元素
	 * 
	 * @param data
	 * @param left
	 * @param right
	 * @return
	 */
	private static int median3(int[] data, int left, int right){
		int center = (left + right) / 2;
		if(data[left] > data[center]){
			swap(data,left,center);
		}
		if(data[left] > data[right]){
			swap(data,left,right);
		}
		if(data[center] > data[right]){
			swap(data,center,right);
		}
		swap(data,center,right - 1);
		return data[right - 1];
	}
	
	/**
	 * 交换两个元素
	 * 
	 * @param data
	 * @param first
	 * @param second
	 */
	private static void swap(int[] data,int first,int second) {
		int temp = data[first];
		data[first] = data[second];
		data[second] = temp;
	}
	
	public static void main(String[] args) {
		int[] data = GenerateData.generateData(10);
		QuickSort.quickSort(data, data.length);
		DisplayData.display(data);
	}
}
