package org.work.leetcode;

public class E034_BuildTreeInPost {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if(inorder.length == 0)
			return null;
		return process(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
	}
	
	public TreeNode process(int[] inorder, int[] postorder, int inFrom, int inTo, int postFrom, int postTo){
		if(inFrom > inTo)
			return null;
		//construct root node
		int rootVal = postorder[postTo];
		TreeNode root = new TreeNode(rootVal);

		//get the number of nodes in left subtree
		int leftNum = 0;
		for(int i = inFrom; i <= inTo; i++){
			if(inorder[i] == rootVal)
				break;
			leftNum++;
		}
		
		root.left = process(inorder, postorder, inFrom, inFrom + leftNum - 1, postFrom, postFrom + leftNum - 1);
		root.right = process(inorder, postorder, inFrom + leftNum + 1, inTo, postFrom + leftNum, postTo - 1);
		return root;
	}
}
