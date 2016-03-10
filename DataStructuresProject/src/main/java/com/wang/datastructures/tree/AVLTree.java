package com.wang.datastructures.tree;

/**
 * 平衡二叉查找树
 * 
 * 每个节点的左子树和右子树的高度最多差1的二叉查找树
 * 
 * @author wangyunlong
 *
 */
public class AVLTree {

	private TreeNode root = null;


	/**
	 * 中序遍历启动器
	 */
	public void inOrder(){
		inOrderTraversal(this.root);
	}
	
	/**
	 * 中序遍历核心逻辑
	 * @param node
	 */
	private void inOrderTraversal(TreeNode node){
		if(node == null) {
			return ;
		}
		inOrderTraversal(node.left);
		System.out.print(node.element + " ");
		inOrderTraversal(node.right);
	}
	
	/**
	 * 插入元素启动器
	 * 
	 * @param value
	 */
	public void insert(int value){
		this.root = insert(value,this.root);
	}
	
	/**
	 * 插入元素，调用 左右单旋转和左右双旋转
	 * 
	 * @param value
	 * @param node
	 * @return
	 */
	private TreeNode insert(int value, TreeNode node) {
		if(node == null) {
			node = new TreeNode(value, null, null, 0);
		}else {
			if( value < node.element) {
				node.left = insert(value, node.left);
				if(height(node.left) - height(node.right) == 2) {
					if(value < node.left.element)
						node = singleRotateWithLeft(node);
					else
						node = doubleRotateWithLeft(node);
				}
			}else if( value > node.element){
				node.right = insert(value, node.right);
				if(height(node.right) - height(node.left) == 2) {
					if( value > node.right.element)
						node = singleRotateWithRight(node);
					else
						node =doubleRotateWithRight(node);
				}
			}
		}
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
	private TreeNode singleRotateWithLeft(TreeNode node) {
		TreeNode leftNode = node.left;
		node.left = leftNode.right;
		leftNode.right = node;
		
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		leftNode.height = Math.max(height(leftNode.left), height(leftNode.right)) + 1;
		
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
	private TreeNode singleRotateWithRight(TreeNode node) {
		TreeNode rightNode = node.right;
		
		node.right = rightNode.left;
		rightNode.left = node;
		
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		rightNode.height = Math.max(height(rightNode.left), height(rightNode.right)) + 1;
		
		return rightNode;
	}
	
	/**
	 * 左右
	 * 先右旋（左子树），在左旋（根）
	 *  k1 < k2 < k3
	 * 			k3		先k1右旋				k2
	 * 		   /  \		再k3左旋			  /  	\
	 * 		  k1   D	------->		 k1		 k3
	 * 		 /	\						/	\	/	\
	 * 		A	 k2					   A	 B C	 D
	 * 			/   \
	 * 		   B	 C
	 * 
	 * @param node
	 * @return
	 */
	private TreeNode doubleRotateWithLeft(TreeNode node) {
		node.left = singleRotateWithRight(node.left);
		return singleRotateWithLeft(node);
	}
	
	/**
	 * 右左
	 * 
	 * 			k1		先右子树左旋转					k2
	 * 		   /   \	再根节点右旋转				  /	   \
	 * 		  A		k3  ------------>			k1		k3
	 * 			   /  \						  /	  \	   /   \
	 * 			  k2   D					 A	   B  C     D
	 * 			 /	\
	 * 			B	 C
	 * 
	 * @param node
	 * @return
	 */
	private TreeNode doubleRotateWithRight(TreeNode node) {
		node.right = singleRotateWithLeft(node.right);
		return singleRotateWithRight(node);
	}
	
	/**
	 * 计算给定节点的树的高度
	 * @param node
	 * @return
	 */
	private int height(TreeNode node) {
		if(node == null) {
			return -1;
		}else
			return node.height;
	}
	
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		tree.insert(3);
		tree.insert(2);
		tree.insert(1);
		tree.insert(4);
		tree.insert(5);
		tree.insert(6);
		tree.insert(7);
		tree.insert(16);
		tree.insert(15);
		tree.insert(14);
		tree.insert(13);
		tree.insert(12);
		tree.insert(11);
		tree.insert(10);
		tree.insert(8);
		tree.insert(9);
		
		tree.inOrder();
		
	}
	
	static class TreeNode{
		int element;
		TreeNode left;
		TreeNode right;
		int height;
		public TreeNode(int value, TreeNode left, TreeNode right,int height) {
			this.element = value;
			this.left = left;
			this.right = right;
			this.height = height;
		}
	}
}
