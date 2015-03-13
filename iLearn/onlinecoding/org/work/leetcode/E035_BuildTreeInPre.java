package org.work.leetcode;

public class E035_BuildTreeInPre {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Start typing your Java solution below
        // DO NOT write main() function
		if(inorder.length == 0)
			return null;
		return process(inorder, preorder, 0, inorder.length - 1, 0, preorder.length - 1);
    }
	
	public TreeNode process(int[] inorder, int[] preorder, int inFrom, int inTo, int preFrom, int preTo){
		if(inFrom > inTo)
			return null;
		
		int rootVal = preorder[preFrom];
		TreeNode root = new TreeNode(rootVal);

		//get the number of nodes in left subtree
		int leftNum = 0;
		for(int i = inFrom; i <= inTo; i++){
			if(inorder[i] == rootVal)
				break;
			leftNum++;
		}
		
		root.left = process(inorder, preorder, inFrom, inFrom + leftNum - 1, preFrom + 1, preFrom + leftNum);
		root.right = process(inorder, preorder, inFrom + leftNum + 1, inTo, preFrom + leftNum + 1, inTo);
		return root;
	}
}
