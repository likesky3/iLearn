package org.work.leetcode;

import java.util.ArrayList;

public class E052_RestoreIPAddresses {
	
	
	public static void main(String[] args) {
		E052_RestoreIPAddresses obj = new E052_RestoreIPAddresses();
		
		ArrayList<String> res = obj.restoreIpAddresses("010010");
		System.out.println(res);
	}
	
	
	public ArrayList<String> restoreIpAddresses(String s) {
		ArrayList<String> res = new ArrayList<String>();
		if(s.length() < 4)
			return res;
		
		patterns = new ArrayList<Integer[]>();
		pattern = new Integer[4];
		//step1: generate all possible IP patterns use DFS
		//IP patterns: if(length == 6), potential patterns are [1, 1, 1, 3], [1,2,2,1],[1,3,1,1]...
		dfs(0, 4, 0, s.length());
		
		//step2: check IP translated according each pattern is a valid IP address
		for(Integer[] patt : patterns){
			StringBuffer ip = new StringBuffer();
			if(validAddr(s, patt, ip))
				res.add(ip.toString());
		}
		
		return res;
	}
	
//	private ArrayList<Integer[]> patterns = new ArrayList<Integer[]>();
//	private Integer[] pattern = new Integer[4];
	
	//do initialization as above will get Runtime Error in OLJ, not know why
	private ArrayList<Integer[]> patterns;
	private Integer[] pattern;
	
	//0.10.01.10 is not valid IP
	private boolean validAddr(String origin, Integer[] structure, StringBuffer ip){
		int start = 0;
		for(int n : structure){
			int end = start + n;
			String sub = origin.substring(start, end);
			if( n > 1 && (sub.startsWith("0") || Integer.parseInt(sub) > 255))
				return false;
			ip.append(sub);
			ip.append(".");
			start = end;
		}
		ip.deleteCharAt(ip.length() - 1);
		return true;
	}
	
	private void dfs(int dep, int maxDep, int sum, int length){
		if(dep == maxDep){
			if(sum == length ){
				//newPatt is a copy of pattern, we should not add pattern directly to patterns, or will end only one elements in patterns
				Integer[] newPatt = new Integer[4];
				int i = 0;
				for(int num: pattern)
					newPatt[i++] = num;
				patterns.add(newPatt);
			}
			return;
		}
		
		for(int i = 1; i <= 3; i++){
			pattern[dep] = i;
			dfs(dep + 1, maxDep, sum + i, length);
		}
	}
}
