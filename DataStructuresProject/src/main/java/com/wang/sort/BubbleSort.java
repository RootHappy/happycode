package com.wang.sort;

import com.wang.tools.DisplayData;
import com.wang.tools.GenerateData;

/**
 * 冒泡排序
 * 
 * @author wangyunlong
 *
 */
public class BubbleSort {

	public static void bubbleSort(int[] data, int length){
		int minIndex = 0;
		for(int i = 0; i < length - 1; i++) {
			minIndex = i;
			for(int j = i + 1; j < length; j++){
				if(data[minIndex] > data[j]){
					minIndex = j;
				}
			}
			data[i] = data[minIndex];
		}
	}
	
	public static void main(String[] args) {
		int[] data = GenerateData.generateData(10);
		BubbleSort.bubbleSort(data, data.length);
		DisplayData.display(data);
		
	}
	
}
