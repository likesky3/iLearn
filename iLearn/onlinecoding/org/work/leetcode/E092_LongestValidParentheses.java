package org.work.leetcode;

import java.util.LinkedList;

public class E092_LongestValidParentheses {

	public static void main(String[] args) {
		E092_LongestValidParentheses obj = new E092_LongestValidParentheses();
		int l = obj.longestValidParentheses("())()()");
		System.out.println(l);
	}
	
	//)(((((()())()()))()(()))(
	public int longestValidParentheses(String s) {
		int maxLen = 0, last = -1;
		LinkedList<Integer> lefts = new LinkedList<Integer>();
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == '(') {
				lefts.push(i);
			} else {
				if (lefts.isEmpty()) {
					// no matching left
					last = i;
				} else {
					// find a matching pair
					lefts.pop();
					if (lefts.isEmpty()) {
						maxLen = Math.max(maxLen, i - last);
					} else {
						maxLen = Math.max(maxLen, i - lefts.peek());
					}
				}
			}
		}
		return maxLen;
	}

}
