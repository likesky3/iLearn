package org.work.leetcode;

import java.util.ArrayList;

public class E025_PathSumII {
	public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return res;
		ArrayList<Integer> item = new ArrayList<Integer>();
		tranversal(root, sum, 0, res, item);
		res = new ArrayList<ArrayList<Integer>>();
		tranversal(root, sum, res, item);
		return res;
	}

	private void tranversal(TreeNode root, int sum, int pathSum,
			ArrayList<ArrayList<Integer>> res, ArrayList<Integer> item) {

		item.add(root.val);

		// @ leaf level, check and add into result if equals sum.
		if (root.left == null && root.right == null) {
			if (pathSum + root.val == sum) {
				ArrayList<Integer> itemcp = new ArrayList<Integer>(item);
				res.add(itemcp);
			}
			item.remove(item.size() - 1);
			return;
		}

		if (root.left != null) {
			tranversal(root.left, sum, pathSum + root.val, res, item);
		}

		if (root.right != null) {
			tranversal(root.right, sum, pathSum + root.val, res, item);
		}

		// remove the impact of current node, i.e. root.val, root may be a child
		// of higher level
		item.remove(item.size() - 1);
	}

	// Version 2: other's
	// Strategy: subtract the node value from the sum when recurring down,
	// and check to see if the sum is 0 when you reach a leaf node.
	private void tranversal(TreeNode root, int sum,
    		ArrayList<ArrayList<Integer>> res, ArrayList<Integer> item) {

		item.add(root.val);
		int subsum = sum - root.val;
		// @ leaf level, check and add into result if equals sum.
		if (subsum == 0 && root.left == null && root.right == null) {
			ArrayList<Integer> itemcp = new ArrayList<Integer>(item);
			res.add(itemcp);
			item.remove(item.size() - 1);
            return;
		}

		if (root.left != null) {
			tranversal(root.left, subsum, res, item);
		}

		if (root.right != null) {
			tranversal(root.right, subsum, res, item);
		}

		// remove the impact of current node, i.e. root.val, root may be a child
		// of higher level
		item.remove(item.size() - 1);
	}
}
