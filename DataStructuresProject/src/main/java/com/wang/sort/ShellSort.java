package com.wang.sort;

/**
 * 希尔排序（Shell Sort）
 * 使用一个增量序列（h[1],h[2],...,h[t]）,只要h[1] = 1，任何增量序列都是可行的。
 * 当增量为1时，实际执行插入排序
 * 
 * 在使用增量h[k]的一趟排序之后，对于每一个i,有A[i] <= A[i+h[k]];
 * 
 * h[k]-排序：对于h[k],h[k]+1,h[k]+2...N-1中的每一个i,把其上的元素放到i,i-h[k],i-2h[k],i-3h[k]..中间的正确位置上
 * 
 * 目前最好的增量序列(Sedgewick)：{1,5,19,41,109....}
 * 
 * */
public class ShellSort {
	
	public static void main(String[] args) {
		int[] data = {2,5,-1,0,32,12,100,-100,34,-5,25};
		System.out.println("Before the data is sorted.");
		display(data);
		shellSort(data,data.length);
		display(data);
		System.out.println("After the data is sorted.");
		
		int[] data2 = {2,5,-1,0,32,12,100,-100,34,-5,25};
		System.out.println("Before the data is sorted.");
		display(data2);
		HibbardShellSort(data2, data2.length);
		display(data2);
		System.out.println("After the data is sorted.");
	}
	
	/**
	 * 执行常见的增量序列 h[t] = N /2; h[k] = (h[k+1])/2;
	 * @param data 出入数组，呆排序
	 * @param length 数组长度
	 * */
	private static void shellSort(int[] data , int length){
		int temp;
		int j;
		for (int increment = length / 2;increment > 0; increment /= 2) { //增量序列
			for(int i = increment; i < length;i++) {
				temp = data[i];
				for(j = i;j >= increment; j -= increment) {
					if(data[j - increment] > temp)
						data[j] = data[j - increment];
					else
						break;
				}
				data[j] = temp;
			}
			
		}
	}
	
	/**
	 * 增量序列：1，3,7 15... 2k -1;
	 * */
	private static void HibbardShellSort(int[] data , int length){
		int temp ,j;
		int[] incrementSequence = generateSequence(length);
		for(int k = incrementSequence.length - 1; k >=0; k--) {
			int increment = incrementSequence[k];
			for( int i = increment; i < length; i++) {
				temp = data[i];
				for(j = i; j >= increment; j-=increment) {
					if(data[j - increment] > temp)
						data[j] = data [j -increment];
					else
						break;
				}
				data[j] = temp;
			}
		}

	}
	
	/**
	 * 生成小于length的 1，3,7 15... 2k -1;序列
	 * @param length (> 0)提供的整数
	 * @return	返回对应的序列
	 * */
	private static int[] generateSequence(int length){
		int count = 0;
		while(length != 1) {
			length = length / 2;
			count ++;
		}
		int[] result = new int[count];
		for( int i = 0; i < count; i++) {
			result [i] = (int)Math.pow(2, i+1) - 1;
		}
		return result;
	}
	
	
	/**
	 * 按照顺序显示数组中的元素
	 * */
	private static void display(int[] data){
		System.out.println("Current Data :");
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
}
