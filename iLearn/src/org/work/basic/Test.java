package org.work.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

import org.work.leetcode.TreeNode;

enum Season {
	SPRING, SUMMER, FALL, WINTER
}

public class Test {

    class WrappedNum{
        int value;
        int idx;
        public WrappedNum(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }
    class WrappedNumComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            int value1 = ((WrappedNum)o1).value;
            int value2 = ((WrappedNum)o2).value;
            if (value1 > 0 && value2 > 0 || (value1 < 0 && value2 < 0))
                return value1 - value2;
            else if (value1 > 0)
                return 1;
            else
                return -1;
        }
        
    }
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2)
            return false;
        WrappedNum[] numsWrapped = new WrappedNum[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsWrapped[i] = new WrappedNum(nums[i], i);
        }
        Arrays.sort(numsWrapped, new WrappedNumComparator());
        for (int i = 1; i < nums.length; i++) {
            int currValue = numsWrapped[i].value;
            for (int j = i - 1; j >= 0 && (currValue - numsWrapped[j].value) > 0 && (currValue - numsWrapped[j].value) <= t; j--) {
                if ((Math.abs(numsWrapped[i].idx - numsWrapped[j].idx)) <= k) {
                    return true;
                }
            }
        }
        return false;
    } 
    
	public void print(int[] num) {
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
	    int[] nums = {1, 3, 6, 2};
	    Test obj = new Test();
	    System.out.println(obj.containsNearbyAlmostDuplicate(nums,1, 2));
	}

	public int rangeBitwiseAnd(int m, int n) {
        long result = 0;
        long helper = 1 << 31;
        while ((m & helper) > 0 && (n & helper) > 0) {
            result |= helper;
            helper >>= 1;
        }
        return (int)result;
    }
	
	public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int num = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    boolean flag = (j > 0 && grid[i][j - 1] == 1) ||(i > 0 && grid[i - 1][j] == 1);
                    if (!flag)
                        num++;
                }
            }
        }
        return num;
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
