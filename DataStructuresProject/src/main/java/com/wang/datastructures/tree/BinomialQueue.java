package com.wang.datastructures.tree;

/**
 * 二项队列
 * 
 * 是一个森林，每种高的树，只有一颗
 * 节点数：1,2,4,8...
 * 
 * 
 * @author wangyunlong
 *
 */
public class BinomialQueue {
	
	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	
	BinQueue queue = null;
	
	public BinomialQueue(){
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public BinomialQueue(int capacity){
		queue = new BinQueue(capacity);
	}
	
	public int deleteMin(){
		if(this.queue.currentSize == 0) {
			return -1;
		}
		int minTree =-1;
		int minItem = -1;
		for(int i = 0,j = 1; j <= this.queue.currentSize;i++,j *= 2) {
			if(this.queue.theTress[i]!= null 
					&& this.queue.theTress[i].element < minItem){
				minItem = this.queue.theTress[i].element;
				minTree = i;
			}
		}
		if(minTree < 0){
			return -1;
		}
		
		BinNode deleteTree = this.queue.theTress[minTree];
		BinNode deleteNode = deleteTree;
		deleteTree = deleteNode.leftChild;
		
		BinQueue deleteQueue = new BinQueue((1 << minTree) - 1);
		deleteQueue.currentSize = (1 << minTree) - 1;
		
		for(int i = minTree - 1; i >= 0; i-- ){
			deleteQueue.theTress[i] = deleteTree;
			deleteTree = deleteTree.nextSibing;
			deleteQueue.theTress[i].nextSibing = null;
		}
		
		this.queue.theTress[minTree] = null;
		this.queue.currentSize -= deleteQueue.currentSize + 1;
		
		this.queue = merge(this.queue,deleteQueue);
		return minItem;
	}
	
	/**
	 * 合并二项队列
	 * 
	 * @param queue
	 */
	public void merge(BinQueue queue) {
		this.queue = merge(this.queue,queue);
	}
	
	
	/**
	 * 合并两个二项队列
	 * 
	 * @param h1
	 * @param h2
	 * @return
	 */
	private BinQueue merge(BinQueue h1, BinQueue h2) {
		if(h1.currentSize + h2.currentSize > h1.capacitye) {
			throw new IllegalArgumentException("merge would excedd capacity.");
		}
		BinNode t1;
		BinNode t2;
		BinNode carry = null;
		int operatorNum = 0;
		h1.currentSize += h2.currentSize;

		for(int i = 0,j = 1; j <= h1.currentSize;i++,j *= 2) {
			t1 = h1.theTress[i];
			t2 = h2.theTress[i];
			operatorNum = computerEmpty(t1) + computerEmpty(t1) * 2 + computerEmpty(carry) * 4;
			switch(operatorNum) {
			case 0:		//No trees
			case 1:		//Only t1
				break;
			case 2:		//Only t2
				h1.theTress[i] = t2;
				h2.theTress[i] = null;
				break;
			case 3:		//T1 and t2
				carry = combineTress(t1, t2);
				h1.theTress[i] = null;
				h2.theTress[i] = null;
				break;
			case 4:		//Only carry
				h1.theTress[i] = carry;
				carry = null;
				break;
			case 5:		//t1 and carry
				carry = combineTress(t1, carry);
				h1.theTress[i] = null;
				break;
			case 6:		//t2 and carry
				carry = combineTress(t2, carry);
				h2.theTress[i] = null;
				break;
			case 7:		// all three
				h1.theTress[i] = carry;
				carry = combineTress(t1, t2);
				h2.theTress[i] = null;
				break;
			}
			
		}
		return h1;
	}
	
	/**
	 * 计算树是否为空
	 * @param node
	 * @return
	 */
	private static int computerEmpty(BinNode node){
		if(node == null) {
			return 0;
		}else{
			return 1;
		}
	}
	/**
	 * 合并两个拥有相同高度的子树
	 * @param t1
	 * @param t2
	 * @return
	 */
	private BinNode combineTress(BinNode t1 , BinNode t2){
		if(t1.element > t2.element)
			return combineTress(t2, t1);
		t2.nextSibing = t1.leftChild;
		t1.leftChild = t2;
		return t1;
	}

	/**
	 * 队列节点
	 * @author wangyunlong
	 *
	 */
	static class BinNode{
		int element;
		BinNode leftChild;
		BinNode nextSibing;
		
		public BinNode(int element, BinNode left,BinNode next){
			this.element = element;
			this.leftChild = left;
			this.nextSibing = next;
		}
	}
	
	/**
	 * 二项队列
	 * @author wangyunlong
	 *
	 */
	static class BinQueue {
		int currentSize;
		int capacitye;
		BinNode[] theTress;
		
		public BinQueue(int capacity){
			if(capacity < 0) {
				throw new IllegalArgumentException("The capacity is error.");
			}
			int count = capacity;
			int i = 1;
			while(count != 1){
				i++;
				count = count / 2;
			}
			theTress = new BinNode[i];
			this.capacitye = capacity;
			currentSize = 0;
		}
	}
	
}
