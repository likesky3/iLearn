package org.work.leetcode;

public class E026_MinimumDepthTree {
	public int minDepth(TreeNode root) {
		// Start typing your Java solution below
		// DO NOT write main() function
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;
		int res = Integer.MAX_VALUE;
		if (root.left != null)
			res = minDepth(root.left) + 1;
		if (root.right != null) {
			int right = minDepth(root.right) + 1;
			res = right < res ? right : res;
		}
		return res;
	}
}
