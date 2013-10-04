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
	
	public void flatten2(TreeNode root) {
        if(root == null)
            return;
        TreeNode right = root.right;
        
        
        if(root.left != null){
            root.right = root.left;
            
            TreeNode rightmost = root.left;
            while(rightmost.right != null)
                rightmost = rightmost.right;
                
            rightmost.right = right;
            
            root.left = null;
            
        }
        
        if(root.right != null)
            flatten(root.right);
        
    }
	
	public static void main(String[] args){
		E022_FlattenTree2List obj = new E022_FlattenTree2List();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		
		obj.flatten2(root);
	}
}
