package com.wang.datastructures.graph;

public class Graph {
	
	private static final int DEFAULT_VERTEX_NUMBER = 10;
	
	private GraphNode[] vertexs;
	
	private int vertexSize;
	
	public Graph() {
		this(DEFAULT_VERTEX_NUMBER);
	}
	
	public Graph(int numVertex){
		vertexs = new GraphNode[numVertex];
		vertexSize = numVertex;
		for(int i = 0; i < vertexSize; i++) {
			vertexs[i] = new GraphNode(i, null);
		}
	}

	public GraphNode[] getVertexs() {
		return vertexs;
	}

	public void setVertexs(GraphNode[] vertexs) {
		this.vertexs = vertexs;
	}

	public int getVertexSize() {
		return vertexSize;
	}

	public void setVertexSize(int vertexSize) {
		this.vertexSize = vertexSize;
	}
	
	/**
	 * 返回节点的入度
	 * @return
	 */
	public int[] vertexIndegree(){
		int[] indegree = new int[this.vertexSize];
		for(int i = 0; i < this.vertexSize; i++) {
			GraphNode node = this.vertexs[i];
			while(node.next != null) {
				node = node.next;
				if(indegree[node.element] == -1) {
					indegree[node.element] = 1;
				}else{
					indegree[node.element]++;
				}
			}
		}
		return indegree;
	}
	
	public void subVertexIndegree(GraphNode node,int[] src){
		while(node.next != null) {
			node = node.next;
			src[node.element]--;
		}
	}
	
	/**
	 * 增加一条边到图中
	 * @param first
	 * @param second
	 */
	public void addEdge(int first, int second){
		vertexNumberRange(first,second);
		if( !isExist(this.vertexs[first], second) ) {
			GraphNode temp = new GraphNode(second, this.vertexs[first].next);
			this.vertexs[first].next = temp;
		}
	}
	
	/**
	 * 查找该链表中是否有该值
	 * @param node
	 * @param value
	 * @return
	 */
	private boolean isExist(GraphNode node, int value){
		while(node != null) {
			if(node.element == value) {
				return true;
			}
			node = node.next;
		}
		return false;
	}
	
	/**
	 * 验证参数是否越界
	 * @param param
	 * @return
	 */
	private boolean vertexNumberRange(int... param){
		for(int i = 0; i < param.length;i++) {
			if(param[i] < 0 || param[i] >= vertexSize){
				throw new IllegalArgumentException("param is error: " + param[i]);
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Graph graph = new Graph(7);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 5);
		graph.addEdge(3, 2);
		graph.addEdge(3, 5);
		graph.addEdge(3, 6);
		graph.addEdge(4, 6);
		graph.addEdge(4, 3);
		graph.addEdge(6, 5);
		int[] value = graph.vertexIndegree();
		for(int i = 0;i < value.length; i++) {
			System.out.print(value[i] + " ");
		}
	}

	/**
	 * 图的节点
	 * @author wangyunlong
	 *
	 */
	static class GraphNode {
		int element;
		GraphNode next;
		public GraphNode(int element, GraphNode next) {
			this.element = element;
			this.next = next;
		}
	}
	
}
