/**
 * SumNumbers.java 
 *
 * Copyright (c) 2011 State Street Bank and Trust Corp.
 * 225 Franklin Street, Boston, MA 02110, U.S.A.
 * All rights reserved.
 *
 * SumNumbers.java is the copyrighted,
 * proprietary property of State Street Bank and Trust Company and its
 * subsidiaries and affiliates which retain all right, title and interest therein."
 * 
 * Revision History
 * 
 * Date            Programmer                Notes
 * ------------    --------------------      ----------------------------
 * May 16, 2013 	   a524690		     Init
 *
 */
package org.work.leetcode;

/**
 * @author a524690
 * A smarter and clearer solution from Internet.
 * DFS: return sum when reach leaf node, otherwise pass sum through DFS.
 */
public class E009_SumNumbersV2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(4);
		TreeNode left1 = new TreeNode(9);
		TreeNode left2 = new TreeNode(0);
		root.left = left1;
		left1.left = left2;
		TreeNode right = new TreeNode(1);
		root.right = right;
		System.out.println(E009_SumNumbersV2.sumNumbers(root));

	}

	public static int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return root.val;

		int sum = 0;
		return tranverseDF(root, sum);
	}

	public static int tranverseDF(TreeNode node, int sum) {
		
		if (node == null )
			return 0;

		sum = sum * 10 + node.val;
		
		//@ leaf node
		if(node.left == null && node.right == null) 
			return sum;
		
		return tranverseDF(node.left, sum) + tranverseDF(node.right, sum);
		
	}
}

