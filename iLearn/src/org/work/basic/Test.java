package org.work.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.work.leetcode.TreeNode;

enum Season {
	SPRING, SUMMER, FALL, WINTER
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
public class Test {

    public static void main(String[] args) {
        Test obj = new Test();
        long absa = Integer.MIN_VALUE;
        System.out.println(-absa);
        Math.pow(2, 3);
        StringBuilder ss = new StringBuilder("abc");
        ss.insert(ss.indexOf("b"), "1");
        System.out.println(ss.toString());
        
    }
    
    public int mySqrt(int x) {
        if (x <= 0)
            return 0;
        int left = 1, right = x / 2 + 1;
        int mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            long midSquare = mid * mid;
            if (midSquare <= x && x < (mid + 1) * (mid + 1))
                return mid;
            else if (midSquare > x)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return right;
    }
    
    int[] nums = {1,2,3,4,5,6,7,8,9};
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (k <= 0 || n <= 0)
            return result;
        recur(n, k, 0, new ArrayList<Integer>(), result);
        System.out.println(result.size());
        return result;
    }
    
    public void recur(int sum, int k, int start, List<Integer> item, List<List<Integer>> result) {
        if (sum <= 0 || item.size() >= k) {
            if (sum == 0 && item.size() == k)
                result.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            item.add(nums[i]);
            recur(sum - nums[i], k, i + 1, item, result);
            item.remove(item.size() - 1);
        }
    }
    Comparator<Interval> comparator = new Comparator<Interval>() {
        public int compare(Interval inter1, Interval inter2) {
            if (inter1.start > inter2.start) return 1;
            else if (inter1.start < inter2.start) return -1;
            else if (inter1.end < inter2.start) return -1;
            else if (inter1.end > inter2.end) return 1;
            else return 0;
        }
    };
       
    public List<Interval> merge(List<Interval> intervals) {
        Interval[] array = intervals.toArray(new Interval[intervals.size()]);
        Arrays.sort(array, comparator);
        // Caution! 从Arrays.asList得到的链表不支持add() 和 remove()操作，使用Iterator也不行
        List<Interval> sorted = Arrays.asList(array);
        intervals = new ArrayList<Interval>(sorted);
        Iterator<Interval> iter = intervals.iterator();
        while (iter.hasNext()) {
            Interval curr = iter.next();
            if (!iter.hasNext()) break;
            Interval next = iter.next();
            if (next. start <= curr.end) {
                if (next.start == curr.start) {
                    Interval tmp = curr;
                    curr = next;
                    next = tmp;
                } else if (next.end > curr.end) {
                    curr.end = next.end;
                }
                iter.remove();
            }
        }
        return intervals;
    }
    public List<Interval> merge1(List<Interval> intervals) {
        Interval[] array = intervals.toArray(new Interval[intervals.size()]);
        Arrays.sort(array, comparator);
        intervals = Arrays.asList(array);
        int i = 0;
        while (i < intervals.size() - 1) {
            if (intervals.get(i + 1). start <= intervals.get(i).end) {
                if (intervals.get(i + 1).start == intervals.get(i).start) {
                    intervals.remove(i);
                } else if (intervals.get(i + 1).end <= intervals.get(i).end) {
                    intervals.remove(i + 1);
                } else {
                    intervals.get(i).end = intervals.get(i + 1).end;
                    intervals.remove(i + 1);
                }
            }
            i++;
        }
        return intervals;
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0)
            return true;
        // build graph
        ArrayList<HashSet<Integer>> graph = new ArrayList<HashSet<Integer>>(numCourses);
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new HashSet<Integer>());
        }
        int m = prerequisites.length;
        for (int i = 0; i < m; i++) {
            if (graph.get(prerequisites[i][0]).add(prerequisites[i][1]))
                indegree[prerequisites[i][1]]++;
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        int counter = 0;
        while (!queue.isEmpty()) {
            counter++;
            int curr = queue.poll();
            for (int w : graph.get(curr)) {
                if (--indegree[w] == 0) queue.offer(w);
            }
        }
        return counter == numCourses;
    }
    
    
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

	
	public int myAtoi(String str) throws Exception{
        if (str == null || str.length() == 0)
            return 0;
        int val = 0;
        boolean isPositive = true;
        str = str.trim();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                int tmp = val * 10;
                if (tmp > 0) {throw new Exception("Overflow");}
                tmp = tmp - (c - '0');
                if (tmp > 0) {throw new Exception("Overflow");}
                val = tmp;
            } else if (c == '-') {
                isPositive = false;
            }
        }
        if (isPositive) {
            if (val == Integer.MIN_VALUE) {throw new Exception("Overflow");}
            return -val;
        } else {
            return val;
        }
    }
	
	public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return null;
        String shortest = strs[0];
        int n = strs.length;
        for (int i = 1; i < n; i++) {
            if (strs[i].length() < shortest.length())
                shortest = strs[i];
        }
        String prefix = null;
        for (int j = shortest.length(); j >= 1; j++) {
            prefix = shortest.substring(0, j);
            int i = 0;
            while (i < n) {
                if (strs[i].startsWith(prefix)) 
                    i++;
                else
                    break;
            }
            if (i == n)
                return prefix;
        }
        return "";
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
