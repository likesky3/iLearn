package org.work.careerup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Delayed;

public class Recursion {
	public static void main(String[] args){
//		ArrayList<Integer> set = new ArrayList<Integer>();
//		set.add(1);
//		set.add(2);
//		
//		for(ArrayList<Integer> subset : getSubset2(set, 0))
//			System.out.println(subset);
		
		//8.5
		int n = 2;
		printParentheses(n, n, new char[n << 1], 0);
	}
	
	//8.3
	public static ArrayList<ArrayList<Integer>> getSubset(ArrayList<Integer> set){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int max = 1 << set.size();
		for(int i = 0; i < max; i++){
			ArrayList<Integer> subset = new ArrayList<Integer>();
			int j = i;
			int idx = 0;
			while(j > 0){
				if((j & 1) == 1)
					subset.add(set.get(idx));
				j >>= 1;
				idx++;
			}
			res.add(subset); //remember 
		}
		return res;
	}
	
	public static ArrayList<ArrayList<Integer>> getSubset2(ArrayList<Integer> set, int start){
		ArrayList<ArrayList<Integer>> allSubsets;
		if(start == set.size()){
			allSubsets = new ArrayList<ArrayList<Integer>>();
			allSubsets.add(new ArrayList<Integer>());//empty subset
		}else{
			allSubsets = getSubset2(set, start + 1);
			ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<ArrayList<Integer>>(); 
			int currItem = set.get(start);
			for(ArrayList<Integer> subset : allSubsets){
				ArrayList<Integer> newSubset = new ArrayList<Integer>();
				newSubset.add(currItem);
				newSubset.addAll(subset);
				moreSubsets.add(newSubset);
			}
			allSubsets.addAll(moreSubsets);
		}
		return allSubsets;
	}
	
	//8.5 print all valid combinations of n-pair parentheses
	public static void printParentheses(int left, int right, char[] str, int count){
		if(left < 0 || right < 0 || left > right)//invalid state
			return;
		if(left == 0 && right == 0) // find one
			System.out.println(str);
		else{
			if(left > 0){
				str[count] = '(';
				printParentheses(left - 1, right, str,count + 1);
			}
			if( right > left){
				str[count] = ')';
				printParentheses(left, right - 1, str, count + 1);
			}
		}
	}
	
	//8.6 paint fill
	public static boolean paintFill(Color[][] screen, int r, int c, Color nColor){
		int rows = screen.length;
		int cols = screen[0].length;
		if(r < 0 || r >= rows || c < 0 || c >= cols)//out of bound
			return false;
		
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(r, c));
		Point[] deltas = {new Point(0, -1), new Point(0, 1), new Point(-1, 0), new Point(1, 0)};
		while(!queue.isEmpty()){
			Point curr = queue.poll();
			screen[r][c] = nColor;
			for(Point delta : deltas){
				Point neigh = new Point(curr.row + delta.row, curr.col + delta.col);
				if(isValidPoint(screen, neigh.row, neigh.col, nColor))
					queue.offer(neigh);
			}
		}
		return true;
	}
	
	private static boolean isValidPoint(Color[][] screen, int r, int c, Color nColor){
		if(r < 0 || r >= screen.length || c < 0 || c >= screen[0].length)//out of bound
			return false;
		if(screen[r][c] == nColor) //reach edge
			return false;
		else
			return true;
	}
}

enum Color{
	RED, GREEN, BLUE
}

class Point{
	int row;
	int col;
//	Color color;
	Point(int row, int col){
		this.row = row;
		this.col  = col;
//		this.color = color;
	}
}