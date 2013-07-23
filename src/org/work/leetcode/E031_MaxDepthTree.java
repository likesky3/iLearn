package org.work.leetcode;

public class E031_MaxDepthTree {
	public int maxDepth(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null)
            return 0;
        int ldepth = 0;
        int rdepth = 0;
        if(root.left != null)
            ldepth = maxDepth(root.left);
        if(root.right != null)
            rdepth = maxDepth(root.right);
        return (ldepth > rdepth ? ldepth : rdepth) + 1;
        
    }
}
