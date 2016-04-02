package com.wang.datastructures.tree;

/**
 * 自顶向下伸展树
 * 
 * @author wangyunlong
 *
 */
public class SplayTree {
	
	
	private SplayNode tree = null;
	
	/**
	 * 删除一个元素
	 * 
	 * @param value
	 */
	public void remove(int value) {
		SplayNode node = null;
		if(this.tree == null) {
			this.tree = splay(value, this.tree);
			if(value == this.tree.element){
				if(this.tree.left == null) {
					node = this.tree.right;
				}else{
					node = this.tree.left;
					node = splay(value,node);
					node.right = this.tree.right;
				}
				this.tree = node;
			}
		}
	}
	
	/**
	 * 插入元素
	 * 
	 * @param value
	 */
	public void insert(int value){
		SplayNode newNode = new SplayNode(value, null, null);
		if(this.tree == null) {
			this.tree = newNode;
		}else{
			this.tree = splay(value,this.tree);
			if(value < this.tree.element) {
				newNode.left = this.tree.left;
				newNode.right = this.tree;
				this.tree.left = null;
				this.tree = newNode;
			}else if(value > this.tree.element) {
				newNode.right = this.tree.right;
				newNode.left = this.tree;
				this.tree.right = null;
				this.tree = newNode;
			}
		}
		
	}
	
	/**
	 * 执行旋转操作
	 * 
	 * @param value
	 * @param node
	 * @return
	 */
	private SplayNode splay(int value, SplayNode node){
		SplayNode header = new SplayNode(-1, null, null);
		SplayNode leftTreeMax = header;
		SplayNode rightTreeMin = header;
		while(value != node.element) {
			if(value < node.element) {
				if(value < node.left.element)
					node = singleRotateWithLeft(node);
				if(node.left == null) 
					break;
				rightTreeMin.left = node;
				rightTreeMin = node;
				node = node.left;
			}else{
				if(value > node.right.element)
					node = singleRotateWithRight(node);
				if(node.right == null)
					break;
				leftTreeMax.right = node;
				leftTreeMax = node;
				node = node.right;
			}
		}
		leftTreeMax.right = node.left;
		rightTreeMin.left = node.right;
		node.left = header.right;
		node.right = header.left;
		return node;
	}
	
	/**
	 * 左左 单旋转
	 * 
	 * 				k2					k1
	 * 			  /	   \	旋转		   /   \
	 * 			 k1		Z	--->	  X		k2
	 * 			/	\					   /   \
	 * 		   X	 Y					  Y		Z
	 * @param node
	 * @return
	 */
	private SplayNode singleRotateWithLeft(SplayNode node) {
		SplayNode leftNode = node.left;
		node.left = leftNode.right;
		leftNode.right = node;
		return leftNode;
	}
	
	/**
	 * 右右
	 * 
	 * 			k1							k2
	 * 		   /   \		旋转			   /  \
	 * 		  X		k2		---->		  k1   Z
	 * 			   /   \				 /	\
	 * 			  Y		Z				X	 Y
	 * 		
	 * @param node k1
	 * @return
	 */
	private SplayNode singleRotateWithRight(SplayNode node) {
		SplayNode rightNode = node.right;
		node.right = rightNode.left;
		rightNode.left = node;
		return rightNode;
	}
	

	public static void main(String[] args) {

	}
	
	static class SplayNode {
		int element;
		SplayNode left;
		SplayNode right;
		
		public SplayNode(int value, SplayNode left, SplayNode right){
			this.element = value;
			this.left = left;
			this.right = right;
		}
	}

}
