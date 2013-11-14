package org.work.basic;

import java.util.ArrayList;

/**
 * 思路：依次生成长度为2 到 9的密码串。
 * 生成长度为len的密码串，先确定首数字first，首数字依次设为1 - 9，
 * 然后用剩余的数字生成长度为len - 1的密码串，
 * len - 1的字符串的首个数字必须是和first能相连的。
 * 由于密码不能重复，用used[]数组记录已用过的数字，
 * 每当一数字被使用后，在used[]中做标记。
 * neighbors[]数组是存储和每个数字相连的所有数字。
 * */
public class Solution {

	public static void main(String[] args) {
		
		Solution obj = new Solution();
		for(String s: obj.generatePwd())
			System.out.println(s);
		
	}
	
	public ArrayList<String> generatePwd(){
		ArrayList<String> res = new ArrayList<String>();
		neighbors = new int[10][];
		neighbors[0] = new int[]{1,2,3,4,5,6,7,8,9};
		neighbors[1] = new int[]{2, 4, 5};
		neighbors[2] = new int[]{1, 3,4, 5,6};
		neighbors[3] = new int[]{2, 5, 6};
		neighbors[4] = new int[]{1,2,5,7,8};
		neighbors[5] = new int[]{1,2,3,4,6,7,8,9};
		neighbors[6] = new int[]{2,3,5,8,9};
		neighbors[7] = new int[]{4,5,8};
		neighbors[8] = new int[]{4,5,6,7,9};
		neighbors[9] = new int[]{5,6,8};
		used = new boolean[10];
		
		int start = 0;
		for(int len = 2; len <= 9; len++){
//			for(start = 1; start < 10; start++)
			for(StringBuilder item: build(0, len)){
//				item.insert(0, start);
				res.add(item.toString());
			}
		}
			
		return res;
	}
	private int[][] neighbors;
	private boolean[] used;
	public ArrayList<StringBuilder> build(int prev, int len){
		ArrayList<StringBuilder> res = new ArrayList<StringBuilder>();
		if(len == 1){
			for(int next : neighbors[prev]){
				if(!used[next]){
					used[next] = true;
					StringBuilder str = new StringBuilder(next);
						res.add(str);
					}
					used[next] = false;
				}
			return res;
		}

		for(int next : neighbors[prev]){
			if(!used[next]){
				used[next] = true;
				for(StringBuilder str : build(next, len - 1)){
					str.insert(0, next);
					res.add(str);
				}
				used[next] = false;
			}
		}
		
		return res;
	}

}
