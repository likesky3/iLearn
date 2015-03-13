package org.work.leetcode;

public class E060_ContainerWithMostWater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * Version 1, got time limit exceeded @ large
	 */
	public int maxArea(int[] height) {
		int max = 0;
		for(int i = 0; i < height.length; i++){
			for(int j = i + 1; j < height.length; j++){
				int curr = (j - i) * (height[i] >= height[j] ? height[j] : height[i]);
				max = max >= curr ? max : curr;
			}
		}
		return max;
	}
	
	/**
	 * Version 2
	 */
	public int maxArea2(int[] height) {
		int max = 0;
		int i = 0;
		int j = height.length - 1;
		while(i < j){
			int area = (j - i) * (height[i] >= height[j] ? height[j] : height[i]);
			max = max < area ? area : max;
			
			//when height[i] < height[j], j-- are ensure to get no larger area
			//since (j - i) will be smaller, the other factor if change, also change smaller
			if(height[i] < height[j])
				i++;
			else {
				j--;
			}
		}
		return max;
	}
}
