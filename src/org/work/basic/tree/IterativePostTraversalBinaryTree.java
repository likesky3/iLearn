package org.work.basic.tree;

import java.util.LinkedList;

import org.work.leetcode.TreeNode;


public class IterativePostTraversalBinaryTree {

	public static void main(String[] args) {
		TreeNode node0 = new TreeNode(1);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);
		TreeNode node3 = new TreeNode(4);
		TreeNode node4 = new TreeNode(5);
		TreeNode node5 = new TreeNode(6);
		TreeNode node6 = new TreeNode(7);
		node0.left = node1;
		node0.right = node2;
		node1.left = node3;
		node1.right = node4;
		node4.left = node5;
		node4.right = node6;
		postTraversal1(node0);
		System.out.println();
		postTraversal2(node0);
	}
	
	public static void postTraversal1(TreeNode root){
		if(root == null)
			return;
		
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.push(root);
		TreeNode curr = null;
		TreeNode prev = null;
		
		while(!stack.isEmpty()){
			curr = stack.peek();
			if(curr.left == null && curr.right == null){//leaf node
				prev = stack.pop();
				System.out.print(prev.val + "\t");
			}else if(prev == null || (prev != curr.left && prev != curr.right)){//child has not been visited
				if(curr.right != null)
					stack.push(curr.right);
				if(curr.left != null)
					stack.push(curr.left);
			}else{//children have been visited, visit root
				prev = stack.pop();
				System.out.print(prev.val + "\t");
			}
		}
	}
	
	public static void postTraversal2(TreeNode root){
		if(root == null)
			return;
		
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.push(root);
		TreeNode curr = null;
		TreeNode prev = null;
		
		while(!stack.isEmpty()){
			curr = stack.peek();
			if(isLeaf(curr) || childrenVisited(curr, prev)){//leaf node
				prev = stack.pop();
				System.out.print(prev.val + "\t");
			}else{
				if(curr.right != null)
					stack.push(curr.right);
				if(curr.left != null)
					stack.push(curr.left);
			}
		}
	}
	
	private static boolean isLeaf(TreeNode root){
		 return root.left == null &&  root.right == null;
	}
	private static boolean childrenVisited(TreeNode curr, TreeNode prev){
		return prev != null && (prev == curr.left || prev == curr.right);
	}

}
