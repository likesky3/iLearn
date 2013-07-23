package org.work.others;

import java.util.ArrayList;

/*
 * given a string, output substrings which are enclosed by parentheses
 * and there is no more embedded parenthesis in those substrings
 * i.e."))abc(zasg(hello1)(addg(dag2))adg)(adg3))"
 * ))abc(zasg(hello1)(addg(dag2))adg)(adg3))
 * 00---1----2------22----3----34---11----10
 * RR---L----L------RL----L----RR---RL----RL
 * output substring(i,j) iff level[i] == level[j] && isleft[i] = true, isleft[j] == false; 
 * output:hello1 + dag2 + adg3*/
public class GetStrInInnerestParenthesis {
	public static void main(String[] args){
		GetStrInInnerestParenthesis obj = new GetStrInInnerestParenthesis();
		System.out.println(obj.getSubstring("))abc(zasg(hello1)(addg(dag2))adg)(adg3))"));
	}
	
	public ArrayList<String> getSubstring(String str){
		ArrayList<String> res = new ArrayList<String>();
		if(str == null)
			return res;
		
		//traversal the given string and marked depth level of each parentheses
//		boolean deeper = false;
		boolean isleft = false; //if last parenthesis is a left parenthesis
		ArrayList<Integer> index = new ArrayList<Integer>();
		ArrayList<Integer> level = new ArrayList<Integer>();
		int c = 0;
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == '('){
				index.add(i);
				if(isleft)
					level.add( (c > 0) ? (level.get(c-1)) + 1 : 1);
				else{
					isleft = true;
//					deeper = true;
					level.add((c > 0 && level.get(c-1) > 0) ? level.get(c-1) : 1);
				}
				c++;
			}else if(str.charAt(i) == ')'){
				index.add(i);
				if(isleft){
//					deeper = false;
					isleft = false;
					level.add(c > 0 ? level.get(c - 1) : 0);
				}else {
					level.add((c > 0 && level.get(c - 1) > 1) ? level.get(c -1) - 1 : 0);
				}
				c++;
			}
			
		}
		
		int lastLevel = 0;
		int iStart = 0;
		isleft = false; //reset isleft to begin a new traversal
		for(int i = 0; i < level.size(); i++){
			int currLevel = level.get(i);
			if(currLevel > lastLevel){
				iStart = index.get(i);
				isleft = true;
			}else if(currLevel == lastLevel && lastLevel != 0){
				if(isleft == false){
					isleft = true;
					iStart = index.get(i);
				}else{
					isleft = false;
					res.add(str.substring(iStart + 1, index.get(i)));
					
				}
			}else{
				isleft = false;
			}
			lastLevel = currLevel;
		}
		
		return res;
	}
}
