package com.wang.somequestion;

/**
 * 最大子序列和
 * 
 * 如果输入全是负数，则默认为0最大
 * 
 * @author wangyunlong
 *
 */
public class MaxSubSequenceSum {
	
	/**
	 * 时间复杂度 O（N）
	 * 
	 * @param data
	 * @param length
	 * @return
	 */
	public static int maxSubSequenceSumO_n(int[] data, int length){
		int maxSum = 0;
		int thisSum = 0;
		for(int i = 0; i < length; i++) {
			thisSum += data[i];
			if(thisSum > maxSum	){
				maxSum = thisSum;
			}else if(thisSum < 0) {
				thisSum = 0;
			}
		}
		return maxSum;
	}
	
	/**
	 * 时间复杂度 O(N * logN)
	 * 
	 * 算法的启动器，用分治策略，来进行算法思想的设计
	 * 
	 * @param data
	 * @param length
	 * @return
	 */
	public static int maxSubSequenceSumO_nlogn(int[] data, int length){
		return maxSubSum(data, 0, length - 1);
	}
	
	/**
	 * 分治策略实现求最大子序列和的核心思想
	 * 
	 * @param data
	 * @param left
	 * @param right
	 * @return
	 */
	private static int maxSubSum(int[] data, int left, int right){
		
		if(left == right){		//基准情况
			if(data[left] > 0)
				return data[left];
			else
				return 0;
		}
		
		int center = (left + right) / 2;
		int maxLeftSum = maxSubSum(data, left, center);
		int maxRightSum = maxSubSum(data, center + 1,right);
		
		int maxLeftBorderSum = 0;
		int leftBorderSum = 0;
		for(int i = center; i >= left; i--){
			leftBorderSum += data[i];
			if(leftBorderSum > maxLeftBorderSum) {
				maxLeftBorderSum = leftBorderSum;
			}
		}
		int maxRightBorderSum = 0;
		int rightBorderSum = 0;
		for(int i = center + 1; i <= right; i++){
			rightBorderSum += data[i];
			if(rightBorderSum > maxRightBorderSum) {
				maxRightBorderSum = rightBorderSum;
			}
		}
		return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
	}
	
	private static int max3(int first, int second, int last){
		int max = first;
		if( max < second){
			max = second;
		}
		if(max < last){
			max = last;
		}
		return max;
	}
	
	/**
	 * 时间复杂度 O(n^2)
	 * 
	 * @param data
	 * @param length
	 * @return
	 */
	public static int maxSubSequenceSumO_2(int[] data, int length){
		int thisSum = 0;
		int maxSum = 0;
		for(int i = 0; i < length; i++) {
			thisSum = 0;
			for(int j = i; j < length; j++) {
				thisSum += data[j];
				if(thisSum > maxSum){
					maxSum = thisSum;
				}
			}
		}
		return maxSum;
	}
	
	/**
	 * 时间复杂度	O(n^3)
	 * 
	 * @param data
	 * @param length
	 * @return
	 */
	public static int maxSubSequenceSumO_3(int[] data, int length){
		int thisSum = 0;
		int maxSum = 0;
		for(int i = 0; i < length;i++) {
			for(int j = i; j < length; j++){
				thisSum = 0;
				for(int k = i; k <= j; k++) {
					thisSum += data[k];
				}
				if(thisSum > maxSum){
					maxSum = thisSum;
				}
			}
		}
		return maxSum;
	}
	
	public static void main(String[] args) {
		int[] data = {4, -3, 5, -2,-1,2,6,-2};
//		int maxSum = maxSubSequenceSumO_3(data,data.length);
//		int maxSum = maxSubSequenceSumO_2(data,data.length);
//		int maxSum = maxSubSequenceSumO_nlogn(data,data.length);
		int maxSum = maxSubSequenceSumO_n(data, data.length);
		System.out.println(maxSum);
	}
	
}
