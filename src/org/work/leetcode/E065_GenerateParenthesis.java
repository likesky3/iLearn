package org.work.leetcode;

import java.util.ArrayList;

public class E065_GenerateParenthesis {

	public static void main(String[] args) {
		E065_GenerateParenthesis obj = new E065_GenerateParenthesis();
		ArrayList<String> res = obj.generateParenthesis3(3);
		for(String s : res){
			System.out.println(s);
		}
	}
	
	//version 1, not completely correct
	//n = 4, not include (()())()
	public ArrayList<String> generateParenthesis(int n){
		ArrayList<String> item = new ArrayList<String>();
		if(n <= 0)
			return item;
		
		ArrayList<ArrayList<String>> store = new ArrayList<ArrayList<String>>();
		item.add("");
		store.add(item); //store[0]: []
		
		//i: total pairs parenthesis in store[i]
		//j: number of prefix parenthesis, e.g. (())(), j = 2
		for(int i = 1; i <= n; i++){
			//list contains i pairs parenthesis
			ArrayList<String> list = new ArrayList<String>();
			//number of prefix ( ranges from [1..i]
			StringBuffer left = new StringBuffer();
			StringBuffer right = new StringBuffer();
			for(int j = 1; j < i; j++){
				//currently, number of prefix ( is j.
				left.append('(');
				right.append(')');
				
				StringBuffer right1 = new StringBuffer();
				StringBuffer right2 = new StringBuffer(right);
				//number of ) ranges from[1..j]
				for(int m = 1; m <= j; m++){
					right1.append(')');
					right2.deleteCharAt(0);
					
					for(String insertItem : store.get(i - j)){
						String newItem = left.toString() + right1.toString() + insertItem + right2.toString();
						 list.add(newItem);
					}
				}
			}
			
			//special for j == i
			left.append('(');
			right.append(')');
			String newItem = left.toString() + right.toString();
			list.add(newItem);
			
			store.add(list);
		}
		
		return store.get(n);	
	}
	
	//version 2, BEST. well-formed parentheses means:
	//[1] number of ( & ) are equal
	//[2] at any position, number of ( is no less than )
	public ArrayList<String> generateParenthesis2(int n){
		ArrayList<String> res = new ArrayList<String>();
		String sb = "";
		addParen(res, sb, n, n);
		return res;
	}
	
	private void addParen(ArrayList<String> res, String sb, int left, int right){
		if(left == 0 && right == 0){
			String newItem = new String(sb);
			res.add(newItem);
		}
		
		if(left > 0){
			addParen(res, sb + '(', left - 1, right);
		}
		
		if(right > left){
			addParen(res, sb + ')', left, right - 1);
		}
	}
	
	//version 3, some kind of brute force
	public ArrayList<String> generateParenthesis3(int n){
		ArrayList<String> res = new ArrayList<String>();
		String sb = "";
		dfs(0, 2 * n, res, 0, 0, sb);
		return res;
	}
	
	private void dfs(int dep, int maxDep, ArrayList<String> res, int leftNum, int rightNum, String str){
		if(2 * leftNum > maxDep)
			return;
		if(dep == maxDep){
			String newItem = new String(str);
			res.add(newItem);
		}
		
		for(int i = 0; i < 2; i++){
			if(i == 0){
				dfs(dep + 1, maxDep, res, leftNum + 1, rightNum, str + '(');
			}else if(rightNum < leftNum){
				dfs(dep + 1, maxDep, res, leftNum, rightNum + 1, str + ')');
			}
		}
			
	}
		
}
