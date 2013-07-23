package org.work.leetcode;




/*
 * Space O(n)的方法就是自己用stack来模拟inorder traverse ，
 * 然后将输出的结果存在一个vector里面，然后遍历vector找到冲突的对。
 * Space constant的方法就是用递归的方式进行inorder traverse，
 * 然后在遍历的过程中记录一个前驱节点，然后比较前驱节点和当前节点的值，将结果记录下来，最后交换一下就行了。
 * */
public class E042_RecoveryBST {
	
	public static void main(String[] args){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		E042_RecoveryBST obj = new E042_RecoveryBST();
		obj.recoverTree(root);
	}
	public void recoverTree(TreeNode root) {
        if(root == null)
        	return;
        
//        ptr[0]:TreeNode e1 = null;
//        ptr[1]:TreeNode e2 = null;
//        ptr[2]:TreeNode pre = null;
//        getErrorNodes(root, e1, e2, pre);
        TreeNode[] ptr = new TreeNode[3];
        getErrorNodes(root, ptr);
        
        //swap ptr[0] & ptr[1]
        int tmp = ptr[0].val;
        ptr[0].val = ptr[1].val;
        ptr[1].val = tmp;
        
    }
	
	//e1: the first node of the first pair which breaks the BST order
	//e2: the second node of the second pair which breaks the BST order
	//suppose a BST with error: 5, 4,7,2,#,6,13, 14,3,#,#, #, #,#, 1
	//its inorder sequence is: 14,2,3,4,5,6,7,13,1
	//<14,2> is the first error pair,
	//<13,1> is the second error pair
	//since the second pair must come after the first one, 
	//the second node of the second pair must be the small node which should be swapped 
	//to the front. 
	private void getErrorNodes(TreeNode root, TreeNode[] ptr){
		if(root == null)
			return;
		getErrorNodes(root.left, ptr);
		if(ptr[2] != null && ptr[2].val > root.val){//error nodes detected
			if(ptr[0] == null) 
				ptr[0] = ptr[2]; 
			ptr[1] = root;
		}
		ptr[2] = root;
		getErrorNodes(root.right, ptr);
	}
	
}

//review 1: what's the pattern? how to code? 