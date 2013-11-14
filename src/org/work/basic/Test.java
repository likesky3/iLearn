package org.work.basic;

import java.util.LinkedList;

enum Season {
	SPRING, SUMMER, FALL, WINTER
}

public class Test {
	
	public void print(int[] num){
		for(int i = 0; i < num.length; i++){
			System.out.print(num[i] + "\t");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Test obj = new Test();
		char[][] m = {{'0'}};
		System.out.println(obj.longestValidParentheses(")"));
	}
	
	public int longestValidParentheses(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(s == null || s.length() == 0)
            return 0;
        int last = -1, max = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(')
                stack.push(i);
            else{
                if(!stack.isEmpty())
                    last = i;
                else{
                    stack.pop();
                    if(!stack.isEmpty())
                        max = Math.max(max, i - stack.peek());
                    else
                        max = Math.max(max, i - last);
                }
            }
        }
        return max;
    }

}
