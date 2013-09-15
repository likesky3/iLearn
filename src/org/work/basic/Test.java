package org.work.basic;

import java.io.IOException;

import org.work.leetcode.TreeNode;



enum Season{SPRING, SUMMER, FALL, WINTER}

public class Test {
	
	public static void main(String[] args) throws IOException{
		Test test = new Test();
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(-1);
		System.out.println(test.maxPathSum(root));
	}
	
	public int maxPathSum(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return root.val;
            
        subSum = Integer.MIN_VALUE;
        int pathSum = recur(root);
        System.out.println(pathSum);
        System.out.println(subSum);
        if(pathSum > subSum)
            return pathSum;
        else
            return subSum;
    }
    
    private int subSum;
    
    private int recur(TreeNode root){
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return root.val;
        
        int pathL = recur(root.left);
        int pathR = recur(root.right);
        int maxLR = Math.max(pathL, pathR);
        int pathSum = root.val + (maxLR > 0 ? maxLR : 0);
        
        int arcSum;
        if(root.val > 0)
            arcSum = root.val + (pathL > 0 ? pathL : 0) + (pathR > 0 ? pathR : 0);
        else
            arcSum = Math.max(root.val, maxLR);
        
        if(arcSum > subSum)
            subSum = arcSum;
        
        return pathSum;
    }
	
}
