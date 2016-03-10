package com.wang.datastructures.tree;

/**
 * 树结构
 *  
 * @author wangyunlong
 *
 */
public class Tree {
	
	private TreeNode root = null;
	
	private static final String DEFAULT_VALUE = "";
	
	public Tree(){
		this(DEFAULT_VALUE);
	}
	public Tree(String value){
		root = new TreeNode(value, null, null);
	}
	
	/**
	 * 打印树结构启动程序
	 * 
	 */
	public void listTree(){
		listTreeDepth(this.root,0);
	}
	
	/**
	 * 输出树结构，递归核心思想
	 * @param node
	 * @param depth
	 */
	public void listTreeDepth(TreeNode node, int depth){
		printNode(node, depth);
		if(node.firstChild != null) {
			TreeNode temp = node.firstChild;
			while(temp != null) {
				listTreeDepth(temp,depth + 1);
				temp = temp.nextSibling;
			}
		}
		
	}
	
	private void printNode(TreeNode node, int depth){
		for(int i = 0; i < depth; i++) {
			System.out.print("	");
		}
		System.out.println(node.element);
	}
	
	/**
	 * 增加指定的值，位置是根节点开始的路径，用/分割
	 * @param value
	 * @param parentValue
	 */
	public void add(String value, String parentValue){
		TreeNode node =  find(parentValue);
		add(value,node);
	}
	
	/**
	 * 增加指定的值到给定的节点下
	 * 
	 * 采用链表的头部插入方式
	 * @param value
	 * @param parentNode 插入值的父亲节点
	 */
	public void add(String value, TreeNode parentNode){
		if(parentNode == null){
			throw new IllegalArgumentException("The specific parent node is null");
		}
		TreeNode node = new TreeNode(value, null, null);
		TreeNode lastChildNode = parentNode.firstChild;
		if(parentNode.firstChild == null) {
			parentNode.firstChild = node;
			return ;
		}
		node.nextSibling = lastChildNode;
		parentNode.firstChild = node;
	}
	
	/**
	 * 获取指定值的节点（从根节点开始的以/分割的路径）
	 * 
	 * @param value
	 */
	public TreeNode find(String value){
		String[] valueArr = value.split("/");
		TreeNode parentNode = null;
		TreeNode currentNode = this.root;
		for(String str : valueArr) {
			while(currentNode != null && !currentNode.element.equals(str)){
				currentNode = currentNode.nextSibling;
			}
			if(currentNode == null)
				throw new IllegalStateException("The path isn't exist.");
			parentNode = currentNode;
			currentNode = currentNode.firstChild;
		}
		return parentNode;
	}
	
	public static void main(String[] args) {
		Tree tree = new Tree("usr");
		tree.add("bill", "usr");
		tree.add("alex", "usr");
		tree.add("mark", "usr");
		
		tree.add("junk.c", "usr/mark");
		tree.add("course", "usr/mark");
		tree.add("book", "usr/mark");
		
		tree.add("ch3.r", "usr/mark/book");
		tree.add("ch2.r", "usr/mark/book");
		tree.add("ch1.r", "usr/mark/book");
		
		tree.add("cop3530", "usr/mark/course");
		
		tree.add("sum97", "usr/mark/course/cop3530");
		tree.add("spr97", "usr/mark/course/cop3530");
		tree.add("fall96", "usr/mark/course/cop3530");
		
		tree.add("syl.r", "usr/mark/course/cop3530/fall96");
		tree.add("syl.r", "usr/mark/course/cop3530/spr97");
		tree.add("syl.r", "usr/mark/course/cop3530/sum97");
		
		
		tree.add("junk.c", "usr/alex");
		
		tree.add("course", "usr/bill");
		tree.add("work", "usr/bill");
		
		tree.add("cop3212", "usr/bill/course");
		
		tree.add("fall97", "usr/bill/course/cop3212");
		tree.add("fall96", "usr/bill/course/cop3212");
		
		tree.add("prog2.r", "usr/bill/course/cop3212/fall96");
		tree.add("prog1.r", "usr/bill/course/cop3212/fall96");
		tree.add("grades", "usr/bill/course/cop3212/fall96");
		
		tree.add("grades", "usr/bill/course/cop3212/fall97");
		tree.add("prog1.r", "usr/bill/course/cop3212/fall97");
		tree.add("prog2.r", "usr/bill/course/cop3212/fall97");
		
		tree.listTree();
	}
	
	/**
	 * 树结构的节点
	 * 
	 * @author wangyunlong
	 *
	 */
	static class TreeNode {
		String element;
		TreeNode firstChild;
		TreeNode nextSibling;
		public TreeNode(String value , TreeNode firstChild, TreeNode nextSibling){
			this.element = value;
			this.firstChild = firstChild;
			this.nextSibling = nextSibling;
		}
	}
}
