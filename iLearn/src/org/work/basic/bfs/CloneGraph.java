package org.work.basic.bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class CloneGraph {
    
    public static void main(String[] args) {
        CloneGraph obj = new CloneGraph();
        UndirectedGraphNode node = new UndirectedGraphNode(0);
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        node.neighbors.add(node1);
        node1.neighbors.add(node2);
        node2.neighbors.add(node2);
        UndirectedGraphNode ret = obj.cloneGraph(node);
    }
    
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        LinkedList<UndirectedGraphNode> queueClone = new LinkedList<UndirectedGraphNode>();
        queue.offer(node);
        queueClone.offer(clone);
        HashSet<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
        visited.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            UndirectedGraphNode currClone = queueClone.poll();
            for (UndirectedGraphNode neigh : curr.neighbors) {
                if (visited.contains(neigh)) continue;
                UndirectedGraphNode neighClone = new UndirectedGraphNode(neigh.label);
                currClone.neighbors.add(neighClone);
                queue.offer(neigh);
                queue.offer(neighClone);
                visited.add(neigh);
            }
        }
        return clone;
    }
}

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
};
