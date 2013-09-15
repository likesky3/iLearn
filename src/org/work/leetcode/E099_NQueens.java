package org.work.leetcode;

import java.util.ArrayList;

public class E099_NQueens {
	public static void main(String[] args) {
		E099_NQueens obj = new E099_NQueens();
		obj.solveNQueens(2);
	}

	public ArrayList<String[]> solveNQueens(int n) {
		solutions = new ArrayList<String[]>();
		canUse = new boolean[n];
		for(int i = 0; i < n; i++)
			canUse[i] = true;
		yIndex = new int[n];
		doSolveQueens(0, n);
		return solutions;
	}

	

	public void doSolveQueens(int dep, int maxDep) {
		if(dep == maxDep){
			String[] solution = new String[maxDep];
			for(int i = 0; i < maxDep; i++){
				StringBuilder tmp = new StringBuilder();
				for(int j = 0; j < yIndex[i]; j++)
					tmp.append('.');
				tmp.append('Q');
				for(int j = 1; j < (maxDep - yIndex[i]); j++)
					tmp.append('.');
				solution[i] = tmp.toString();
			}
			solutions.add(solution);
			return;
		}
		
		for(int i = 0; i < maxDep; i++){
			if(canUse[i] && isValid(dep, i)){
				canUse[i] = false;
				yIndex[dep] = i;
				doSolveQueens(dep + 1, maxDep);
				canUse[i] = true;
			}
		}
		
	}

	//检查斜线位置上是否冲突
	private boolean isValid(int x, int y) {
		//只需检查x前面的行的皇后位置，若在(x,y)的斜线位置上则return false
		for(int i = 0; i < x; i++){
			if((x - i) == Math.abs(y - yIndex[i]))//判断是否在左右斜上方，仔细体会
				return false;
		}
		return true;
	}
	
	private boolean[] canUse;
	private int[] yIndex;
	//下面第一行的初始化方式在OJ上会导致前一次的结果没有被清空
//	private ArrayList<String[]> solutions = new ArrayList<String[]>();
	private ArrayList<String[]> solutions;
}
