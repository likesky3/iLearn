package org.work.leetcode;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(int x) {
		val = x;
	} 
	
	public static TreeNode buildTree(){
		return buildTree1();
	}
	public static TreeNode buildTree1(){
		TreeNode root = new TreeNode(-1);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(2);
		TreeNode node6 = new TreeNode(-1);
		TreeNode node7 = new TreeNode(1);
		TreeNode node8 = new TreeNode(7);
		root.left = node1;
		root.right = node8;
		node1.left = node2;
		node2.left = node3;
		node2.right = node4;
		node4.left = node5;
		node4.right = node6;
		node6.right = node7;
		return root;
	}
}
