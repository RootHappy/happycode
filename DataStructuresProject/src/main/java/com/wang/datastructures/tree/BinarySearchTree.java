package com.wang.datastructures.tree;

public class BinarySearchTree {
	
	private TreeNode root = null;
	
	
	/**
	 * 先序遍历启动器
	 */
	public void preOrder(){
		preOrderTraversal(this.root);
	}
	
	/**
	 * 中序遍历启动器
	 */
	public void inOrder(){
		inOrderTraversal(this.root);
	}
	
	/**
	 * 后序遍历启动器
	 */
	public void postOrder(){
		posrOrderTraversal(this.root);
	}
	
	/**
	 * 先序遍历核心逻辑
	 * @param node
	 */
	private void preOrderTraversal(TreeNode node) {
		if(node == null) {
			return;
		}
		System.out.print(node.element);
		preOrderTraversal(node.left);
		preOrderTraversal(node.right);
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
		System.out.print(node.element);
		inOrderTraversal(node.right);
	}
	
	/**
	 * 后序遍历二叉树的核心
	 * @param node
	 */
	private void posrOrderTraversal(TreeNode node){
		if(node == null) {
			return ;
		}
		posrOrderTraversal(node.left);
		posrOrderTraversal(node.right);
		System.out.print(node.element);
	}
	
	
	/**
	 * 是否包含某个元素，查找启动器
	 * 
	 * @param value
	 * @return
	 */
	public boolean find(int value){
		TreeNode node = find(value,this.root);
		if(node == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 查找到包含的元素并返回其对应的节点
	 * 
	 * 核心的递归过程
	 * 
	 * @param value
	 * @param node
	 * @return
	 */
	private TreeNode find(int value, TreeNode node) {
		if(node == null) {
			return null;
		}
		if(value < node.element){
			return find(value, node.left);
		}else if(value > node.element) {
			return find(value, node.right);
		}
		return node;
	}
	
	/**
	 * 查找最小元素启动器
	 * @return
	 */
	public int findMin(){
		TreeNode node = findMin(this.root);
		if(node == null) {
			return -1;
		}
		return node.element;
	}
	
	/**
	 * 查找最小元素，递归流程
	 * @param node
	 * @return
	 */
	private TreeNode findMin(TreeNode node){
		if(node == null) {
			return null;
		}
		if(node.left == null) {
			return node;
		}
		return findMin(node.left);
	}
	
	/**
	 * 查找最大的元素值，使用循环
	 * @return
	 */
	public int findMax(){
		TreeNode node = this.root;
		if(node == null)
			return -1;
		while(node.right != null) {
			node = node.right;
		}
		return node.element;
	}
	
	/**
	 * 构建一个树
	 * @param data
	 */
	public void bulidTree(int[] data){
		for(int i = 0; i < data.length; i++) {
			insert(data[i]);
		}
	}
	
	/**
	 * 插入一个元素启动器
	 * @param value
	 */
	public void insert(int value){
		this.root = insert(value,this.root);
	}
	
	/**
	 * 递归插入一个元素到特定位置，如果重复，则不做处理
	 * @param value
	 * @param node
	 * @return
	 */
	private TreeNode insert(int value, TreeNode node) {
		if(node == null){
			TreeNode insertionNode = new TreeNode(value, null, null);
			return insertionNode;
		}
		if(value < node.element) {
			node.left = insert(value, node.left);
		}else if(value > node.element){
			node.right = insert(value, node.right);
		}
		return node;
	}
	
	/**
	 * 删除节点启动器
	 * @param value
	 */
	public void delete(int value){
		delete(value,this.root);
	}
	
	/**
	 * 删除节点
	 * 
	 * 1.如果删除的是叶子节点，直接删除
	 * 2.如果删除的是只有一个孩子的节点，是该节点的父节点直接指向其子节点
	 * 3.如果删除的节点有两个孩子，则找到右子树中的最小值，替换该节点的值，然后删除右节点的最小值
	 * 
	 * @param value
	 * @param node
	 * @return
	 */
	private TreeNode delete(int value, TreeNode node){
		if(node == null) {
			return null;
		}
		if(value < node.element) {
			node.left = delete(value,node.left); 
		}else if(value > node.element) {
			node.right = delete(value , node.right);
		}else {
			if(node.right != null && node.left != null) {//有两个孩子节点
				TreeNode temp = findMin(node.right);
				node.element = temp.element;
				node.right = delete(temp.element, node.right);
			}else{//是叶子节点，或者是单个孩子节点
				if(node.left == null){
					node = node.right;
				}else if(node.right == null){
					node = node.left;
				}
			}
			
		}
		return node;
	}
	
	

	public static void main(String[] args) {
		int[] data = {6,2,1,4,3,8};
		BinarySearchTree tree = new BinarySearchTree();
		tree.bulidTree(data);
//		tree.insert(6);
//		tree.insert(2);
//		tree.insert(1);
//		tree.insert(4);
//		tree.insert(3);
//		tree.insert(8);
		tree.preOrder();
		System.out.println();
		tree.inOrder();
		System.out.println();
		tree.delete(3);
		tree.postOrder();
		System.out.println();
		System.out.println(tree.find(7));
		System.out.println(tree.findMax());
		System.out.println(tree.findMin());
	}
	
	static class TreeNode{
		int element;
		TreeNode left;
		TreeNode right;
		public TreeNode(int element, TreeNode left, TreeNode right){
			this.element = element;
			this.left = left;
			this.right = right;
		}
	}

}
