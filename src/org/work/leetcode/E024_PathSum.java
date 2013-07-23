package org.work.leetcode;


public class E024_PathSum {
	public boolean hasPathSum(TreeNode root, int sum) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null)
            return false;
        return tranversal(root, sum, 0);
            
    }
    
    private boolean tranversal(TreeNode root, int sum, int pathSum){
        if(root.left == null && root.right == null){
            if(pathSum + root.val == sum)
                return true;
            return false;
        }
        
        boolean res = false;
        if(root.left != null){
            res = tranversal(root.left, sum, pathSum + root.val);
            if(res == true)
                return res;
        }
        
        if(root.right != null){
            return res = tranversal(root.right, sum, pathSum + root.val);
        }
        
        return res;
    }
    
    //Version 2: other's
	// Strategy: subtract the node value from the sum when recurring down,
	// and check to see if the sum is 0 when you reach a leaf node.
    public boolean hasPathSum2(TreeNode root, int sum){
    	if(root == null)
        	return false;
    	
        int subSum = sum - root.val;
    	//reached a leaf node and satisfy the requirement
    	if(subSum == 0 && root.left == null && root.right == null)
    		return true;
    	else{
    		//otherwise, check both subtrees
    		if(root.left != null){
    			boolean has = hasPathSum2(root.left, subSum);
    			if(has)
    				return has;
    		}
    		if(root.right != null){
    			return hasPathSum2(root.right, subSum);
    		}
    		return false;
    	}
    }
    

}
