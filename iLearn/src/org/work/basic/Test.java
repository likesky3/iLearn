package org.work.basic;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.work.leetcode.TreeNode;

enum Season {
	SPRING, SUMMER, FALL, WINTER
}

public class Test {

	public void print(int[] num) {
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int a = 1 + + 2;
		System.out.println(a);
	}

	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
	    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(); 
        if (root == null)
            return result;
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<Integer> queue;
        return result;
	}
	public int longestValidParentheses(String s) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		if (s == null || s.length() == 0)
			return 0;
		int last = -1, max = 0;
		LinkedList<Integer> stack = new LinkedList<Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				stack.push(i);
			else {
				if (!stack.isEmpty())
					last = i;
				else {
					stack.pop();
					if (!stack.isEmpty())
						max = Math.max(max, i - stack.peek());
					else
						max = Math.max(max, i - last);
				}
			}
		}
		return max;
	}

}
