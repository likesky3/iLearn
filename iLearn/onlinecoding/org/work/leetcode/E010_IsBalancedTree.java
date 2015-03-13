package org.work.leetcode;

public class E010_IsBalancedTree {
	public boolean isBalanced(TreeNode root) {
        return depth(root) == -1 ? false : true;
    }
    
    public int depth(TreeNode root){
        if(root == null)
            return 0;
        
        int dpL = depth(root.left);
        if(dpL == -1)
            return -1;
        int dpR = depth(root.right);
        if(dpR == -1)
            return -1;
            
        if(Math.abs(dpL - dpR) > 1)
            return -1;
        return (dpL > dpR ? dpL : dpR) + 1;
    }
}
