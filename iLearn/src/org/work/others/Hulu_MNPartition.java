package org.work.others;

public class Hulu_MNPartition {
	public static void main(String[] args){
		Hulu_MNPartition obj = new Hulu_MNPartition();
		obj.doPartition(12, 4);
	}
	
	//m, n均为正整数，将m用不大于n的正整数进行划分，求共有几种划分
	private int doPartition(int m, int n){
		if(m <= 0 || n <= 0)
			return 0;
		//大于m的数不可能构成m的一个划分项
		if(m < n)
			n = m;
		
		int[][] f = new int[m + 1][n + 1];
		//1 initial
		f[0][0] = 1;
		for(int i = 1; i <= m; i++)
			f[i][1] = 1;
		
		for(int i = 2; i <= m; i++){
			for(int j = 2; j <= (i < n ? i : n); j++){
				f[i][j] = 0;
				for(int k = 0; k <= i / j; k++){
					int d1 = i - k * j;
					int d2 = (j - 1) > d1 ? d1 : (j - 1);
					f[i][j] += f[d1][d2];
				}
				System.out.println("f[" + i + "][" + j + "]=" + f[i][j]);
			}
		}
		System.out.println(f[m][n]);
		return f[m][n];
	}
}

/**
 * block 1: 如何将递推式转化为代码？二维数组不熟练*/
