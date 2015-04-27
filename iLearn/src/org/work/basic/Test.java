package org.work.basic;

import java.util.ArrayList;
import java.util.LinkedList;

import org.work.basic.OuterClass.StaticInnerClass;
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
//	    System.out.println(Integer.parseInt("acf0e0bd".substring(0, 7), 16));
//	    System.out.println(Integer.parseInt("2ffe2b07".substring(0, 7), 16));
//	    System.out.println(Integer.parseInt("8744e5de".substring(0, 7), 16));
//	    System.out.println(Integer.parseInt("b12c6e18".substring(0, 7), 16));
	    
	    StaticInnerClass intance = new StaticInnerClass();
	    intance.age2 = 23;
	    System.out.println(intance.hashCode() + " " + intance.age2);
	    StaticInnerClass intance2 = new StaticInnerClass();
	    intance2.age2 = 100;
        System.out.println(intance2.hashCode() + " " + intance.age2 + " " + intance2.age2);
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
