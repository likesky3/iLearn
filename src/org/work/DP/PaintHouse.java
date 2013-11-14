package org.work.DP;


public class PaintHouse {
	public static void main(String[] args){
		PaintHouse obj = new PaintHouse();
		int[][] cost = {{1,100,2},{1,5,3},{100,200,1}};
		System.out.println(obj.minCost(cost));
	}
	
	public int minCost(int[][] cost){
		if(cost == null || cost.length == 0)
				return 0;
		if(cost.length > 1 && cost[0].length == 1)//1 color is not enough to paint more than 2 houses
			return Integer.MAX_VALUE;
		
		int m = cost.length; // m houses
		int n = cost[0].length; // n colors
		
		int[][] dp = new int[2][n];//dp[][i] stores the min cost when the current house choose color i
		for(int i = 0; i < n; i++)
			dp[0][i] = cost[0][i];
		int prev = 0;
		int curr = 1;
		int h = 1;
		
		//loop from the first house to the last one
		while(h < m){
			int[] min2 = find2MinimumCost(dp[prev]);
			for(int i = 0; i < n; i++){
				//choose the smallest cost if it is not produced by prev house choose this color
				//otherwise choose the second smallest
				dp[curr][i] = cost[h][i] + (dp[prev][i] == min2[0] ? min2[1] : min2[0]);
			}
			prev = 1 - prev;
			curr = 1 - curr;
			h++;
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++)
			if(min > dp[prev][i])
				min = dp[prev][i];
		return min;
	}
	
	//find the first and second smallest element in the array
	public int[] find2MinimumCost(int[] prevcost){
		int[] min2 = new int[2];
		min2[0] = Integer.MAX_VALUE;
		min2[1] = Integer.MAX_VALUE;
		for(int i = 0; i < prevcost.length; i++){
			if(prevcost[i] < min2[0])
				min2[0] = prevcost[i];
			else if(prevcost[i] < min2[1])
				min2[1] = prevcost[i];
		}
		return min2;
		
	}
}
