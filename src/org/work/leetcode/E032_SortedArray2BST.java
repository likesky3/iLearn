package org.work.leetcode;

public class E032_SortedArray2BST {
	 public TreeNode sortedArrayToBST(int[] num) {
		 if(num.length == 0)
			 return null;
		 return process(num, 0, num.length - 1);
	 }
	 
	 private TreeNode process(int[] num, int from, int to){
		 int mid = (from + to) / 2;
		 int leftTo = mid - 1;
		 int rightFrom = mid + 1;
		 TreeNode root = new TreeNode(num[mid]);
		 if(leftTo >= from){
			 int leftFrom = from;
			 root.left = process(num, leftFrom, leftTo);
		 }
		 if(rightFrom <= to){
			 int rightTo = to;
			 root.right = process(num, rightFrom, rightTo);
		 }
		 return root;
	 }
}
