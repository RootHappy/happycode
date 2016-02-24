package com.wang.sort;

import com.wang.datastructres.priorityqueue.BinaryHeap;
import com.wang.tools.DisplayData;
import com.wang.tools.GenerateData;

public class HeapSort {
	
	public static int[] heapSort(int[] data , int length){
		BinaryHeap heap = new BinaryHeap(data.length);
		heap.buildHeap(data);
		int[] result = new int[data.length];
		for(int i = 0; i < data.length;i++ ){
			result[i] = heap.deleteMin();
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] data = GenerateData.generateData(10);
		DisplayData.display(data);
		DisplayData.display(heapSort(data, data.length));
	}

}
