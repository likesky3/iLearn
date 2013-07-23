package org.work.leetcode;

//one time, bug free.
//6.12 50m
//tree, recursion, pre-order traversal
//analysis start from the basic cases
//coding start with skeleton, and modify it with test cases 
public class E022_FlattenTree2List {
	public void flatten(TreeNode root) {
		// Start typing your Java solution below
		// DO NOT write main() function
		if (root == null)
			return;
		doFlatten(root);
	}

	public TreeNode doFlatten(TreeNode root) {
		if (root == null || (root.left == null && root.right == null))
			return root;

		TreeNode right = root.right;
		TreeNode lastOfLeft = null;
		if (root.left != null) {
			lastOfLeft = doFlatten(root.left);
			root.right = root.left;
			root.left = null;
		}

		if (right != null) {
			TreeNode lastOfRight = doFlatten(right);
			if (lastOfLeft != null)
				lastOfLeft.right = right;
			return lastOfRight;
		}

		return lastOfLeft;
	}
}
