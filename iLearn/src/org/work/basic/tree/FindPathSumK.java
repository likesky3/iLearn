package org.work.basic.tree;

import java.util.ArrayList;

import org.work.leetcode.TreeNode;

public class FindPathSumK {
	public void findPath(TreeNode root, int sum, ArrayList<TreeNode> path) {
		if (root == null)
			return;

		path.add(root);
		int tmp = 0;
		for (int i = path.size() - 1; i >= 0; i--) {
			tmp += path.get(i).val;
			if (tmp == sum) {
				print(path, i);
			}
		}
		findPath(root.left, sum, path);
		findPath(root.right, sum, path);
		path.remove(path.size() - 1);
	}
	public void print(ArrayList<TreeNode> path, int level){
		if(path != null ){
			int last = path.size() - 1;
			for(int i = level; i <= last; i++)
				System.out.print(path.get(i).val + "\t");
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		TreeNode root = TreeNode.buildTree();
		FindPathSumK obj = new FindPathSumK();
		ArrayList<TreeNode> path = new ArrayList<TreeNode>();
		obj.findPath(root, 6, path);
	}
}
