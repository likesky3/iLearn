package org.work.spotoffer;

import java.util.Scanner;

public class E020_PrintMatrix {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int rows = 0, cols = 0;
		while(cin.hasNext()){
			rows = cin.nextInt();
			cols = cin.nextInt();
			int[][] m = new int[rows][cols];
			for(int r = 0; r < rows; r++){
				for(int c = 0; c < cols; c++){
					m[r][c] = cin.nextInt();
				}
			}
			E020_PrintMatrix.printMatrix(m);
		}
	}

	public static void printMatrix(int[][] m){
		//check edge case
		if(m == null)
			return;
		
		//initialization, 以下四个变量记录当前遍历圈的状态
		int rows = m.length;
		int cols = m[0].length;
		int col = 0, row = 0; // start point
		int num = rows * cols;
		int[] res = new int[num];
		int k = 0;
		
		//注意测试一行一列的情形，这是循环体中break的意义所在
		while(rows > 0  && cols >0){
			//print the start point in left-up corner
			res[k++] = m[row][col];
			
			//print from left to right
			for(int i = 1; i < cols; i++)
				res[k++] = m[row][++col];
			if(rows == 1)
				break;
			
			//print from up to bottom
			for(int i = 1; i < rows; i++)
				res[k++] = m[++row][col];
			if(cols == 1)
				break;
			
			//print from right to left
			for(int i = 1; i < cols ; i++)
				res[k++] =m[row][--col]; 
			
			//print from bottom to up
			for(int i = 1; i < rows -1; i++)
				res[k++] = m[--row][col];
			
			//update states
			col++; //get new start point;
			rows -= 2;
			cols -= 2;
		}
		for(int i = 0; i < num; i++)
			System.out.print(res[i] + " ");
		System.out.println();
	}
}
