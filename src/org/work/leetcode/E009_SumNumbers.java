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
 *
 */
public class E009_SumNumbers
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
	// TODO Auto-generated method stub
	TreeNode root = new TreeNode(4);
	TreeNode left1 = new TreeNode(9);
	TreeNode left2 = new TreeNode(0);
	root.left = left1;
	left1.left = left2;
	TreeNode right = new TreeNode(1);
	root.right = right;
	System.out.println(E009_SumNumbers.sumNumbers(root));

    }
    
    public static int sumNumbers(TreeNode root)
    {
	if(root == null)
	    return 0;
	if(root.left == null && root.right == null)
	    return root.val;
	
	String branchStr = String.valueOf(root.val);
	int sum = 0;
	
	if(root.left != null)
	    sum = tranverseDF(root.left, branchStr, sum);
	if(root.right != null)
	    sum = tranverseDF(root.right, branchStr, sum);
	
	return sum;
    }
    
    public static int tranverseDF(TreeNode node, String branchStr, int sum)
    {
	StringBuffer buffer = new StringBuffer(branchStr);
	buffer.append(String.valueOf(node.val));
	
	//node is a leaf
	if(node.left == null && node.right == null)
	{
	    int branchValue = Integer.parseInt(buffer.toString());
	    System.out.println(branchValue);
	    return sum + branchValue;
	}
	   
	
	//node is not a leaf
	if(node.left != null)
	    sum = tranverseDF(node.left, buffer.toString(), sum);
	if(node.right != null)
	    sum = tranverseDF(node.right, buffer.toString(), sum);
	return sum;
    }
}

/**
 * History Log
 * V1: testcase{4,9,0,#,1}, java.lang.StackOverflowError
 * 	--L79 & L81, pass node as 1st argument/
 */