package org.work.leetcode;

public class E038_IsValidBST {
	public boolean isValidBST(TreeNode root) {

		if(root == null)
            return true;
        if(root.left == null && root.right == null)
            return true;
            
        if(root.left != null && root.left.val >= root.val)
            return false;
        if(root.right != null && root.right.val <= root.val)
            return false;
        
        if(!isValidBST(root.left) || !isValidBST(root.right))
            return false;
        
        if(root.val < getMax(root.left) && root.val > getMin(root.right))
            return true;
        return false;
    }
    
    private int getMax(TreeNode root){
        if(root.right != null)
            return getMax(root.right);
        else
            return root.val;
    }
    
    private int getMin(TreeNode root){
        if(root.left != null)
            return getMin(root.left);
        else
            return root.val;
    }
}
