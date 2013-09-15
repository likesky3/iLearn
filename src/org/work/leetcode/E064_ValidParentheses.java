package org.work.leetcode;

import java.util.LinkedList;

public class E064_ValidParentheses {
	public static void main(String[] args){
		E064_ValidParentheses obj = new E064_ValidParentheses();
		System.out.println(obj.isValid("()"));
		
	}
	public boolean isValid(String s) {
		if(s == null)
			return true;
		LinkedList<Character> stack = new LinkedList<Character>();
		for(char c : s.toCharArray()){
			if(c == '{' || c == '[' || c == '('){
				stack.push(c);
			}
			else {
				if(stack.isEmpty())
					return false;
				char top = stack.pop();
				boolean isMatch = false;
				switch (c) {
				case '}':
					isMatch = top == '{' ? true : false;
					break;
				case ']':
					isMatch = top == '[' ? true : false;
					break;
				case ')':
					isMatch = top == '(' ? true : false;
					break;
				default:
					break;
				}
				if(!isMatch)
					return false;
			}
		}
		if(!stack.isEmpty())
			return false;
		return true;
	}
}
