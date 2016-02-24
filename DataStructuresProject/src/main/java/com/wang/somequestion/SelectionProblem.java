package com.wang.somequestion;

import java.util.Arrays;

import com.wang.sort.ShellSort;
import com.wang.tools.DisplayData;
import com.wang.tools.GenerateData;

public class SelectionProblem {
	
	public static void main(String[] args) {
		int[] data = GenerateData.generateData(1000);
		DisplayData.display(data);
//		int k = selectionKBySortAll(data,3);
		int k = selectionKBySortPart(data,3);
		System.out.println(k);
	
	}
	
	
	
	/**
	 * 选择第k个元素
	 * 排序数组，选择第k个元素
	 * */
	public static int selectionKBySortAll(int[] data , int k) {
		ShellSort.HibbardShellSortDesc(data, data.length);
		DisplayData.display(data);
		return data[k - 1];
	}
	
	/**
	 * 选择第k个元素
	 * 排序k个元素，其余元素插入
	 * */
	public static int selectionKBySortPart(int[] data , int k) {
		int[] partData = Arrays.copyOf(data, k);
		ShellSort.HibbardShellSortDesc(partData, partData.length);
		int temp , j;
		for(int i = k; i < data.length;i++) {
			temp = data[i];
			for(j = partData.length - 1; j >= 0; j--){
				if(partData[j] < temp) {
					if(j != 0)
						partData[j] = partData[j -1];
					else
						partData[j] = temp;
				}else{
					if(j != partData.length - 1)
						partData[j + 1] = temp;
					break;
				}
			}
		}
		ShellSort.HibbardShellSortDesc(data, data.length);
		DisplayData.display(data);
		DisplayData.display(partData);
		return partData[k - 1];
	}
}
