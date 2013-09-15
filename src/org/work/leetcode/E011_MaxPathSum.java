/**
 * MaxPathSum.java 
 *
 * Copyright (c) 2011 State Street Bank and Trust Corp.
 * 225 Franklin Street, Boston, MA 02110, U.S.A.
 * All rights reserved.
 *
 * MaxPathSum.java is the copyrighted,
 * proprietary property of State Street Bank and Trust Company and its
 * subsidiaries and affiliates which retain all right, title and interest therein."
 * 
 * Revision History
 * 
 * Date            Programmer                Notes
 * ------------    --------------------      ----------------------------
 * May 21, 2013 	   a524690		     Init
 *
 */
package org.work.leetcode;


/**
 * @author a524690
 * 
 */
public class E011_MaxPathSum {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		TreeNode node1 = new TreeNode(-1);
		root.left = node1;
		E011_MaxPathSum obj = new E011_MaxPathSum();
		System.out.println((obj.maxPathSum(root)));
	}

	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;
		currMax = root.val;
		maxWithRoot(root);
		return currMax;
	}

	private int maxWithRoot(TreeNode root) {
		if (root == null)
			return 0;

		int leftSum = maxWithRoot(root.left);
		int rightSum = maxWithRoot(root.right);

		// path which across the root node
		int arcSum = root.val;
		if (leftSum > 0)
			arcSum += leftSum;
		if (rightSum > 0)
			arcSum += rightSum;
		if (currMax < arcSum)
			currMax = arcSum;

		// path which ends at root node
		// arcSum always >= pathSum
		int pathSum = root.val;
		int subMax = Math.max(leftSum, rightSum);
		if (subMax > 0)
			pathSum += subMax;
		return pathSum;
	}

	private int currMax;
}

/**
 * if I initialize currMax with Integer.MIN_VALUE in its declaration, leetcode
 * will get wrong answers for some test cases like {-3} initial currMax in
 * maxPathSum method is OK.
 * */
