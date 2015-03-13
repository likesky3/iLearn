package org.work.spotoffer;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		
		int n = 0, k = 0, bit = 1;
		StringBuilder str = new StringBuilder();

	}
	
	public static boolean check(String str){
//		boolean[] pat = new boolean[];
		return true;
	}
//	private static int[][] treeNode;
//	private static int k;
	public static void dfs(int i, int prev, LinkedList<Integer>path, int[][] treeNode, int k){
		if(treeNode[i][1] == -1 && treeNode[i][2] == -1){//leaf node
			if(prev + treeNode[i][0] == k){
				System.out.print("A path is found: ");
				for(int j = 0; j < path.size(); j++)
					System.out.print(path.get(j) + " ");
				System.out.println(i);
			}
			return;
		}
		
		path.add(i);
		prev += treeNode[i][0];
		dfs(treeNode[i][1], prev, path, treeNode, k);
		dfs(treeNode[i][2], prev, path, treeNode, k);
		path.removeLast();
	}
}
