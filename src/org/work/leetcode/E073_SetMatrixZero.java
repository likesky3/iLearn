package org.work.leetcode;

public class E073_SetMatrixZero {

	public static void main(String[] args) {

	}

	public void setZeroes(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		boolean row0has0 = false;
		boolean col0has0 = false;
		//using first row and col as storage
        //1.search zero in first row and col to determin it's own status
		for (int col = 0; col < cols; col++) {
			if(matrix[0][col] == 0){
				row0has0 = true;
				break;
			}
		}
		
		for (int row = 0; row < rows; row++) {
			if(matrix[row][0] == 0){
				col0has0 = true;
				break;
			}
		}
	    //2.search zeros in other position to sign the first row and col
		for (int row = 1; row < rows; row++) {
			for (int col = 1; col < cols; col++) {
				if (matrix[row][col] == 0) {
					matrix[row][0] = 0;
					matrix[0][col] = 0;
				}
			}
		}

		//3.set zeroes in other positions according to first col and row
//		for (int row = 1; row < rows; row++) {
//			if (matrix[row][0] == 0) {
//				for (int col = 1; col < cols; col++) {
//					matrix[row][col] = 0;
//				}
//			}
//		}
//
//		for (int col = 1; col < cols; col++) {
//			if (matrix[0][col] == 0) {
//				for (int row = 1; row < rows; row++) {
//					matrix[row][col] = 0;
//				}
//			}
//		}
		for (int row = 1; row < rows; row++) {
			for (int col = 1; col < cols; col++) {
				if(matrix[row][0] == 0 || matrix[0][col] == 0)
					matrix[row][col] = 0;
			}
		}
		
		//4.set zeroes for first row and col
		if(row0has0){
			for (int col = 0; col < cols; col++) {
				matrix[0][col] = 0;
			}
		}
		
		if(col0has0){
			for (int row = 0; row < rows; row++) {
				matrix[row][0] = 0;
			}
		}

	}
}
