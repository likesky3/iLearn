package org.work.basic.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

//数组表示图： http://sbp810050504.blog.51cto.com/2799422/1163565
//http://en.wikipedia.org/wiki/Dijkstra's_algorithm
public class Dijkstra {

	public static void main(String[] args) {
		Dijkstra obj = new Dijkstra();
		GraphNode[] graph = obj.buildGraph();
		obj.dijkstra(graph, 0);
			
	}
	
	public GraphNode[] buildGraph(){ 
		GraphNode[] graph = new GraphNode[7];
		for(int i = 0; i < graph.length; i++)
			graph[i] = new GraphNode(i);
		graph[0].adjNodes.add(new AdjNode(1, 2));
		graph[0].adjNodes.add(new AdjNode(3, 1));
		graph[1].adjNodes.add(new AdjNode(3, 3));
		graph[1].adjNodes.add(new AdjNode(4, 10));
		graph[2].adjNodes.add(new AdjNode(0, 4));
		graph[2].adjNodes.add(new AdjNode(5, 5));
		graph[3].adjNodes.add(new AdjNode(2, 2));
		graph[3].adjNodes.add(new AdjNode(4, 2));
		graph[3].adjNodes.add(new AdjNode(5, 8));
		graph[3].adjNodes.add(new AdjNode(6, 4));
		graph[4].adjNodes.add(new AdjNode(6, 6));
		graph[6].adjNodes.add(new AdjNode(5, 1));
		return graph;
	}
	public PriorityQueue<GraphNode> rebuildHeap(PriorityQueue<GraphNode> oldHeap){
		PriorityQueue<GraphNode> newHeap = new PriorityQueue<GraphNode>(oldHeap.size(), new GraphNodeComparator());
		while(!oldHeap.isEmpty()){
			newHeap.offer(oldHeap.poll());
		}
		return newHeap;
	}
	public  void dijkstra(GraphNode[] graph, int source){
		//initialization: 
		//dist_between[source, v] = infinitely, v is any node 
		//dist_between[source, source] = 0
		//all nodes is in set U which means their shortest distance to source is undecided
		int nodesNum = graph.length;
		for(int i = 0; i < nodesNum; i++){
			graph[i].dist = Integer.MAX_VALUE;
		}
		
		graph[source].dist = 0;
		PriorityQueue<GraphNode> Uset= new PriorityQueue<GraphNode>(nodesNum, new GraphNodeComparator());
		for(int i = 0; i < nodesNum; i++)
			Uset.offer(graph[i]);
		
		while(!Uset.isEmpty()){
			//remove node u with shortest distance to source from Uset, add to S
			GraphNode u = Uset.poll();
			if(u.dist == Integer.MAX_VALUE)
				break;
//			System.out.println("***u:" + u.nodeId);
			for(AdjNode v : u.adjNodes){
				int alt = u.dist + v.edge;
//				System.out.print("v:" + v.nodeId + "\t" + "v.dist: " + graph[v.nodeId].dist + "\talt: " + alt + "\t\t");
				if(alt < graph[v.nodeId].dist){
					Uset.remove(graph[v.nodeId]);
					graph[v.nodeId].dist = alt;
					graph[v.nodeId].prevNode = u; 
					Uset.offer(graph[v.nodeId]);
//					Uset = rebuildHeap(Uset); //too space-cost, instead we can remove the old, update the old and insert it again
					
				}
			}
			System.out.println();
		}
		
		//check result
		for(int i = 0; i < nodesNum; i++)
			System.out.println(graph[i].dist);
	}
	
	//check whether priority queue would automatically heapify
		//result is no.
		public void testPriorityQueue(){
			GraphNode[] graph= buildGraph();
			int nodesNum = graph.length;
			PriorityQueue<GraphNode> U = new PriorityQueue<GraphNode>(nodesNum, new GraphNodeComparator());
			graph[0].dist = 0;
			graph[1].dist = 2;
			graph[2].dist = 3;
			graph[3].dist = 1;
			graph[4].dist = 3;
			graph[5].dist = 6;
			graph[6].dist = 5;
			
			for(int i = 0; i < nodesNum; i++)
				U.offer(graph[i]);
			for(int i = 0; i < nodesNum; i++){
				System.out.print(U.poll().dist);
				System.out.print('\t');
			}
			System.out.println();
			//check change
			for(int i = 0; i < nodesNum; i++)
				U.offer(graph[i]);
			graph[0].dist = 10;
			for(int i = 0; i < nodesNum; i++){
				System.out.print(U.poll().dist);
				System.out.print('\t');
			}
		}
}

class GraphNodeComparator implements Comparator<GraphNode>{
	public int compare(GraphNode node1, GraphNode node2){ 
		if(node1.dist == node2.dist)
			return 0;
		else if(node1.dist < node2.dist)
			return -1;
		else
			return 1;
	}
}
class GraphNode {
	int nodeId;
	int dist;
	ArrayList<AdjNode> adjNodes;
	GraphNode prevNode; //recursively call to print the shortest path
	public GraphNode(int id) {
		this.nodeId = id;
		this.dist = Integer.MAX_VALUE;
		this.adjNodes = new ArrayList<AdjNode>();
	}

	void addAdjNode(AdjNode v) {
		this.adjNodes.add(v);
	}
}

class AdjNode {
	int nodeId;
	int edge;

	public AdjNode(int id, int weight) {
		this.nodeId = id;
		this.edge = weight;
	}
}
