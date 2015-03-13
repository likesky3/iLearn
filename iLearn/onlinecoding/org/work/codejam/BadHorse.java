package org.work.codejam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class BadHorse {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader("a.in"));
		PrintWriter out = new PrintWriter(new FileWriter("a.out"));

		int N = Integer.parseInt(in.readLine());
		int k = 1;

		int c;
		HashMap<String, HashSet<String>> graph = new HashMap<String, HashSet<String>>();
		HashMap<String, Integer> colormap = new HashMap<String, Integer>();
		LinkedList<String> queue = new LinkedList<String>();
		String[] points;
		boolean flag = true;
		while(k <= N){
			c = Integer.parseInt(in.readLine());
			if(c == 1){
				out.println("Case #" + k + ": " + "Yes");
				in.readLine();
				k++;
				continue;
			}
			
			//1 build graph
			graph.clear();
			for(int i = 0; i < c; i++){
				points = in.readLine().split(" ");
				buildGraph(graph, points[0], points[1]);
				buildGraph(graph, points[1], points[0]);
			}
			
			//2 verify if the graph is a bipartite graph, use BFS, use queue, mark color then enqueue
			 colormap.clear();
			 queue.clear();
			 flag = true;
			 
			 String startNode = graph.keySet().toArray(new String[1])[0];
			 colormap.put(startNode, 0);
			 queue.offer(startNode);
			 while(!queue.isEmpty()){
				 String v1 = queue.poll();
				 int ucolor = colormap.get(v1) == 0 ? 1 : 0;
				 
				 //for each neighbor node, mark color and enqueue
				 for(String v2: graph.get(v1)){
					 if(!colormap.containsKey(v2)){
						 colormap.put(v2, ucolor);
						 //only enqueue not processed node
						 queue.offer(v2);
					 }else if(colormap.get(v2) != ucolor){//conflict
						 flag = false;
						 break;
					 }
				 }
				 if(!flag)
					 break;
			 }
			 if(flag)
				 out.println("Case #" + k + ": " + "Yes");
			 else
				 out.println("Case #" + k + ": " + "No");
			 
			k++;
		}
		
		in.close();
		out.close();
	}
	
	private static void buildGraph(HashMap<String, HashSet<String>> graph, String v1, String v2){
		if(!graph.containsKey(v1)){
			HashSet<String> neighs = new HashSet<String>();
			neighs.add(v2);
			graph.put(v1, neighs);
		}else{
			graph.get(v1).add(v2);
		}
	}

}
