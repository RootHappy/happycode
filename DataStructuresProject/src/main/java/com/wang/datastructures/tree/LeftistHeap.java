package com.wang.datastructures.tree;

/**
 * 左式堆
 * 
 * 堆序性：任意节点X小/大于其子节点的值
 * 
 * X的零路径长度：从X到一个没用两个儿子的节点的最短路径的长，Npl(null) = -1
 * 
 * 结构性：对于堆中的每一个节点X，左儿子的零路径长至少与右儿子的零路径长一样大。
 * 
 * @author wangyunlong
 *
 */
public class LeftistHeap {
	
	private TreeNode root = null;

	/**
	 * 插入节点
	 * @param value
	 */
	public void insert(int value) {
		TreeNode node = new TreeNode(value, null, null, 0);
		this.root = merge(this.root, node);
	}
	
	/**
	 * 删除最小元素
	 * @return
	 */
	public int deleteMin(){
		if(this.root == null) 
			return -1;
		TreeNode oldRoot = this.root;
		this.root = merge(this.root.left, this.root.right);
		oldRoot.left = null;
		oldRoot.right = null;
		return oldRoot.element;
	}
	
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
	 * 合并两个左式堆
	 * @param heap
	 */
	public void merge(LeftistHeap heap){
		merge(this.root, heap.root);
	}
	
	/**
	 * 合并两个左式堆的节点
	 * 
	 * @param h1
	 * @param h2
	 * @return
	 */
	private static TreeNode merge(TreeNode h1,TreeNode h2) {
		if(h1 == null)
			return h2;
		if(h2 == null)
			return h1;
		if(h1.element < h2.element){
			return merge1(h1,h2);
		}else {
			return merge1(h2,h1);
		}
	}
	
	/**
	 * 合并两个树，并保证左式堆性质
	 * h1的根节点小于h2的根节点
	 * 
	 * @param h1
	 * @param h2
	 * @return
	 */
	private static TreeNode merge1(TreeNode h1, TreeNode h2) {
		if(h1.left == null)
			h1.left = h2;
		else {
			h1.right = merge(h1.right,h2);
			if(h1.left.npl < h1.right.npl) {
				swapChildren(h1);
			}
			h1.npl = h1.right.npl + 1;
		}
		return h1;
	}
	
	/**
	 * 交换左右子树	
	 * @param node
	 */
	private static void swapChildren(TreeNode node) {
		TreeNode temp = node.left;
		node.left = node.right;
		node.right = temp;
	}
	
	public static void main(String[] args) {
		LeftistHeap h1 = new LeftistHeap();
		h1.insert(3);
		h1.insert(8);
		h1.insert(10);
		h1.insert(21);
		h1.insert(14);
		h1.insert(17);
		h1.insert(26);
		h1.insert(23);
		h1.inOrder();
		System.out.println();
		LeftistHeap h2 = new LeftistHeap();
		h2.insert(6);
		h2.insert(12);
		h2.insert(7);
		h2.insert(18);
		h2.insert(24);
		h2.insert(37);
		h2.insert(18);
		h2.insert(33);
		h2.inOrder();
		System.out.println();
		h1.merge(h2);
		h1.inOrder();
		System.out.println();
		System.out.println(h1.deleteMin());
		h1.inOrder();
	}
	
	/**
	 * 树节点，增加零路径长度
	 * @author wangyunlong
	 *
	 */
	static class TreeNode {
		int element;
		TreeNode left;
		TreeNode right;
		int npl;
		public TreeNode(int element, TreeNode left, TreeNode right, int npl) {
			this.element = element;
			this.left = left;
			this.right = right;
			this.npl = npl;
		}
		
	}
	
}
