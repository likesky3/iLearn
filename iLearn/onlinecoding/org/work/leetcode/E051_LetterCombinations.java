package org.work.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

//if a variable will not be changed, set it as global variable instead of arguments
public class E051_LetterCombinations {
	private HashMap<Integer, ArrayList<Character>> map;
	private int maxDepth;
	private  ArrayList<String> res;
	private String num;
	private String[] dict;
	
	public ArrayList<String> letterCombinations2(String digits) {
		res = new ArrayList<String>();
        if(digits.isEmpty()){
        	res.add("");
        	return res;
        }
   
        map = new HashMap<Integer, ArrayList<Character>>();
        for(int i = 2; i <= 6; i++){
        	ArrayList<Character> candidates = new ArrayList<Character>();
        	for(int j = 0; j < 3; j++){
        		char c = (char) ('a' + 3 * (i - 2) + j);
        		candidates.add(c);
        	}
        	map.put(i, candidates);
        }
        for(int i = 7; i <= 9; i++){
        	ArrayList<Character> candidates = new ArrayList<Character>();
        	for(int j = 0; j < 4; j++){
        		char c = (char) ('a' + 3 * (i - 2) + j);
        		candidates.add(c);
        	}
        	map.put(i, candidates);
        }
        map.get(8).remove(0);
        map.get(9).remove(0);
        map.get(9).add('z');
        
        maxDepth = digits.length();
        num = digits;
        StringBuffer sb = new StringBuffer();
        dfs2(0, sb);
        return res;
    }
	
	private void dfs2(int depth, StringBuffer sb){
		if(depth == maxDepth){
			res.add(sb.toString());
			return;
		}
		
		for(char c : map.get(num.charAt(depth) - '0')){
			StringBuffer sbcopy = new StringBuffer(sb);
			sbcopy.append(c);
			dfs2(depth + 1, sbcopy);
		}
			
	}
	
	public ArrayList<String> letterCombinations(String digits) {
		res = new ArrayList<String>();
        if(digits.isEmpty()){
        	res.add("");
        	return res;
        }
        
        //others, neater
        dict =new String[]{"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
//        dict[0] = "abc";
//        dict[1] = "def";
//        dict[2] = "ghi";
//        dict[3] = "jkl";
//        dict[4] = "mno";
//        dict[5] = "pqrs";
//        dict[6] = "tuv";
//        dict[7] = "wxyz";
        
        maxDepth = digits.length();
        num = digits;
        StringBuffer sb = new StringBuffer();
        dfs(0, sb);
        return res;
    }
	
	private void dfs(int depth, StringBuffer sb){
		if(depth == maxDepth){
			res.add(sb.toString());
			return;
		}
		
		for(char c : dict[num.charAt(depth) - '0' - 2].toCharArray()){
			StringBuffer sbcopy = new StringBuffer(sb);
			sbcopy.append(c);
			dfs2(depth + 1, sbcopy);
		}
			
	}
	
	
	public static void main(String[] args){
		E051_LetterCombinations obj = new E051_LetterCombinations();
		System.out.println(obj.letterCombinations("9"));
	}
}
