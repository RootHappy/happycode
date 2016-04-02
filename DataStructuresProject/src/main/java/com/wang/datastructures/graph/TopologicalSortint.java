package com.wang.datastructures.graph;

/**
 * 拓扑排序：针对有向无环图的排序
 * @author wangyunlong
 *
 */
public class TopologicalSortint {
	
	
	public static void topSort(Graph graph){
		int[] indegree = graph.vertexIndegree();
		for(int i = 0; i < graph.getVertexSize(); i++) {
			int vertex = findIndegreeZero(indegree);
			if(vertex == -1){
				throw new RuntimeException("graph has a cycle.");
			}
			System.out.print(vertex + " ");
			indegree[vertex] = -1;
			graph.subVertexIndegree(graph.getVertexs()[i], indegree);
		}
	}
	
	private static int findIndegreeZero(int[] degrees){
		for(int i = 0; i < degrees.length;i++) {
			if(degrees[i] == 0) {
				return i;
			}
		}
		return -1;
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
		
		topSort(graph);
	
		
	}
	
}
