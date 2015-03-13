package org.work.beautyofp;

//step1，计算左上顶点为原点的部分和。ps[row][col]表示坐标[0,0]和[row, col]为对角线的矩形区域和
//step2，计算[row1, col] - [row2,col]表示的矩形区域和
//step3，转化为一维问题求解，枚举上下边界，然后再用一维情况下的方法确定左右边界，得解。
public class E02_15_MaxSubArraySum2D {

	public static void main(String[] args) {

	}

	//step 1, O(m * n)
	public int[][] calcPartSumStartAt00(int[][] a){
		int rows = a.length;
		int cols = a[0].length;
		int[][] ps = new int[rows][cols];
		
		//处理第一行&第一列
		ps[0][0] = a[0][0];
		for(int i = 1; i < cols; i++)
			ps[0][i] = ps[0][i -1 ] + a[0][i];
		for(int i = 1; i < rows; i++)
			ps[i][0] = ps[i -1 ][0] + a[i][0];
		
		//处理剩余区域
		for(int r = 1; r < rows; r++){
			for(int c = 1; c < cols; c++)
				ps[r][c] = ps[r][c - 1] + ps[r - 1][c] - ps[r - 1][c - 1] + a[r][c];
		}
		return ps;
	}
	
	//step 2
	public int[][][] calPartSumBetween2Rows(int[][] a, int[][] ps){
		int rows = ps.length;
		int cols = ps[0].length;
		int[][][] brs = new int[rows][rows][cols];
		
		brs[0][0][0] = a[0][0];
		//first row
		for(int r2 = 0; r2 < rows; r2++){
			for(int c = 1; c < cols; c++)
				brs[0][r2][c] = ps[r2][c] - ps[r2][c - 1];
		}
		
		//first col
		for(int r2 = 1; r2 < rows; r2++){
			brs[0][r2][0] = ps[r2][0];
		}
		for(int r1 = 1; r1 < rows; r1++){
			for(int r2 = r1; r2 < rows; r2++)
				brs[r1][r2][0] = ps[r2][0] - ps[r1 - 1][0];
		}
		
		//the rest
		for(int r1 = 1; r1 < rows; r1++){
			for(int r2 = r1; r2 < rows; r2++)
				for(int c = 1; c < cols; c++)
					brs[r1][r2][c] = ps[r2][c] - ps[r2][c - 1] - ps[r1 - 1][c] + ps[r1 -1][c - 1];
		}
		return brs;
	}
	
	//step 3
	public int maxSum(int[][][] brs){
		int rows = brs.length;
		int cols = brs[0][0].length;
		int max = Integer.MIN_VALUE;
		for(int r1 = 0; r1 < rows; r1++){
			for(int r2 = r1; r2 < rows; r2++){
				int sum = brs[r1][r2][0];
				for(int col = 1; col < cols; col++){
					if(sum > 0)
						sum += brs[r1][r2][col];
					else
						sum = brs[r1][r2][col];
					if(sum > max)
						max = sum;
				}
			}
		}
		return max;
	}
	
}
