package com.wang.datastructures.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionTree {
	
	private TreeNode root = null;
	
	public ExpressionTree(String expression){
		buildExpressionTree(expression);
	}
	
	/**
	 * 
	 * @param expression
	 */
	public TreeNode buildExpressionTree(String expression){
		Deque<TreeNode> stack = new ArrayDeque<>();
		String[] expressionElements = expression.split(" ");
		TreeNode node = null;
		TreeNode left =null;
		TreeNode right = null;
		for(String str : expressionElements) {
			char ch = str.charAt(0);
			node = new TreeNode(ch,null,null);
			if(matcheOperator(str)) {
				right = stack.pop();
				left = stack.pop();
				node.left = left;
				node.right = right;
				stack.push(node);
			}else{
				stack.push(node);
			}
		}
		this.root = stack.pop();
		return this.root;
	}
	
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
	 * 匹配运算符
	 */
	private boolean matcheOperator(String ch){
		Pattern pattern = Pattern.compile("[\\+\\-\\*/]");
		Matcher matcher =  pattern.matcher(ch);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		String data = "a b c * + d e * f + g * +";
		ExpressionTree tree = new ExpressionTree(data);
		tree.postOrder();
		System.out.println();
		tree.inOrder();
		System.out.println();
		tree.preOrder();
	}
	
	static class TreeNode {
		char element;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(char element, TreeNode left , TreeNode right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}
	}
}
