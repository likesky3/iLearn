package org.work.leetcode;

public class E076_Search2DMatrix {
	public static void main(String[] args){
		E076_Search2DMatrix obj = new E076_Search2DMatrix();
		int[][] a = new int[1][2];
		a[0][0] = 1;
		a[0][1] = 3;
		boolean result = obj.searchMatrix(a, 0);
		System.out.println(result);
	}
	
	//Diagonal Binary Search, top right corner or bottom left corner
	public boolean searchMatrix(int[][] matrix, int target) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		int i = 0, j = cols - 1;
		while(i < rows && j >= 0){
			if(matrix[i][j] == target)
				return true;
			else if(matrix[i][j] > target)
				j--;
			else
				i++;
		}
		
		return false;
	}
}
