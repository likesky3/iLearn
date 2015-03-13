package org.work.leetcode;

import java.util.ArrayList;

public class E100_NQueensII {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		E100_NQueensII obj = new E100_NQueensII();
		int res = obj.totalNQueens(4);
		System.out.println(res);
	}
	
	public int totalNQueens(int n) {
		counter = 0;
		canUse = new boolean[n];
		for(int i = 0; i < n; i++)
			canUse[i] = true;
		yIndex = new int[n];
		solveNQueues(0, n);
		return counter;
	}
	
	public void solveNQueues(int dep, int maxDep){
		if(dep == maxDep){
			counter++;
			return;
		}
		for(int i = 0; i < maxDep; i++){
			if(canUse[i] && isValid(dep, i)){
				canUse[i] = false;
				yIndex[dep] = i;
				solveNQueues(dep + 1, maxDep);
				canUse[i] = true;
			}
		}
	}
	
	public boolean isValid(int x, int y){
		for(int i = 0; i < x; i++){
			if((x - i) == Math.abs(y - yIndex[i]))
				return false;
		}
		return true;
	}
	
	private boolean[] canUse;
	private int[] yIndex;
	private int counter;
}
