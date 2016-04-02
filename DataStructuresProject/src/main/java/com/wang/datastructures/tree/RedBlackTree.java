package com.wang.datastructures.tree;

/**
 * 红黑树：着色的二叉查找树
 * 
 * 着色性：
 * 	1.每一个节点要么着红色，要么是黑色	
 * 	2.根是黑色的
 * 	3.如果一个节点是红色的，那么他的子节点必须是黑色的
 * 	4.从一个节点到一个null指针的每一条路径必须包含相同数目的黑色节点
 * 
 * @author wangyunlong
 *
 */
public class RedBlackTree {

	private RedBlackNode tree = null;
	
	
	public RedBlackTree(){
		tree = new RedBlackNode(-100, null, null, ColorType.BLACK);
	}
	
	private RedBlackNode currentNode;
	private RedBlackNode parent;
	private RedBlackNode grandparent;
	private RedBlackNode ggp;
	
	
	public void insert(int value){
		currentNode = this.tree;
		parent = this.tree;
		grandparent = this.tree;
		while(value != currentNode.element) {
			ggp = grandparent;
			grandparent = parent;
			parent = currentNode;
			if(value < currentNode.element)
				currentNode = currentNode.left;
			else
				currentNode = currentNode.right;
			
			if(currentNode.left.color == ColorType.RED
					&& currentNode.right.color == ColorType.RED) {
				handleReorient(value);
			}
			
		}
		
		if(currentNode != null)	//已经存在该值
			return ;
		currentNode = new RedBlackNode(value, null, null, ColorType.BLACK);
		if(value < parent.element) {
			parent.left = currentNode;
		}else{
			parent.right = currentNode;
		}
	}
	
	
	private void handleReorient(int value){
		//修改节点红黑色
		currentNode.color = ColorType.RED;
		currentNode.left.color = ColorType.BLACK;
		currentNode.right.color =ColorType.BLACK;
		//父节点是红色
		if(parent.color == ColorType.RED){
			grandparent.color = ColorType.BLACK;
			if((value < grandparent.element != value < parent.element) ){	//小于大于，大于小于
				parent = rotate(value,grandparent);
			}
			currentNode = rotate(value,ggp);
			currentNode.color = ColorType.BLACK;
		}
		this.tree.right.color = ColorType.BLACK;	//保证根节点为black
		
	}
	
	private RedBlackNode rotate(int value, RedBlackNode node) {
		if(value < node.element) {
			if(value < node.left.element){
				return node.left = singleRotateWithLeft(node.left);
			}else{
				return node.left = singleRotateWithRight(node.left);
			}
		}else{
			if(value < node.right.element){
				return node.right = singleRotateWithLeft(node.right);
			}else{
				return node.right = singleRotateWithRight(node.right);
			}
		}
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
	private RedBlackNode singleRotateWithLeft(RedBlackNode node) {
		RedBlackNode leftNode = node.left;
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
	private RedBlackNode singleRotateWithRight(RedBlackNode node) {
		RedBlackNode rightNode = node.right;
		node.right = rightNode.left;
		rightNode.left = node;
		return rightNode;
	}
	
	
	/**
	 * 打印树元素
	 */
	public void printTree(){
		doPrint(this.tree.right);
	}
	
	/**
	 * 中序遍历打印树元素
	 * @param node
	 */
	private void doPrint(RedBlackNode node){
		if(node != null) {
			doPrint(node.left);
			System.out.println(node.element + " ");
			doPrint(node.right);
		}
	}
	
	/**
	 * 红黑树节点
	 * 
	 * @author wangyunlong
	 *
	 */
	static class RedBlackNode{
		int element;
		RedBlackNode left;
		RedBlackNode right;
		ColorType color;
		
		public RedBlackNode(int value, RedBlackNode left, RedBlackNode right, ColorType color) {
			this.element = value;
			this.left = left;
			this.right = right;
			this.color = color;
		}
	}
	
	/**
	 * 红黑树颜色
	 * @author wangyunlong
	 *
	 */
	enum ColorType{
		RED,BLACK
	}
	
}
