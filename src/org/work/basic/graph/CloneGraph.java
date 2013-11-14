package org.work.basic.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class CloneGraph {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UndirectedGraphNode node = new UndirectedGraphNode(0);
		node.neighbors = new ArrayList<UndirectedGraphNode>();
		node.neighbors.add(node);
		node.neighbors.add(node);
		cloneGraph(node);
	}

	public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        //BFS + hashmap
        if(node == null)
            return null;
            
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.offer(node);
        UndirectedGraphNode node2 = new UndirectedGraphNode(node.label);
        map.put(node.label, node2);
        UndirectedGraphNode curr = null, neigh2 = null;
        
        while(!queue.isEmpty()){
           curr = queue.poll();
           //copy its neighbors
           for(UndirectedGraphNode neigh : curr.neighbors){
                if(map.containsKey(neigh.label))
                    neigh2 = map.get(neigh.label);
                else{
                    neigh2 = new UndirectedGraphNode(neigh.label);
                    map.put(neigh.label, neigh2);
                    queue.offer(neigh);
                }
                map.get(curr.label).neighbors.add(neigh2);
           }
        }
    
        return node2;
    }
}
