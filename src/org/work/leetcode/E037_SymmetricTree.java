package org.work.leetcode;

import java.util.ArrayList;
import java.util.Stack;

public class E037_SymmetricTree {
	public boolean isSymmetric(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null)
            return true;
        if(root.left == null && root.right == null)
            return true;
        return doSymmetricTest(root.left, root.right);
    }
    
	//recursive, without extra space
    private boolean doSymmetricTest(TreeNode left, TreeNode right){
        if(left == null && right == null)
            return true;
        if((left == null && right != null) || (left != null && right == null))
            return false;
        if(left.val != right.val)
            return false;
        if(doSymmetricTest(left.left, right.right) && doSymmetricTest(left.right, right.left))
            return true;
        return false;
    }
    
    //iteratively, extra space: O(N)
    public boolean isSymmetric2(TreeNode root) {
    	
    	Stack<TreeNode> s = new Stack<TreeNode>();
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	TreeNode curr = root;
    	while(!s.isEmpty() || curr != null){
    		if(curr != null){
    			s.push(curr);
    			curr = curr.left;
    		}else{
    			curr = s.pop();
    			list.add(curr.val);
    			curr = curr.right;
    		}
    	}
    	
    	int i = 0, j = list.size() - 1;
    	while(i < j){
    		if(list.get(i) != list.get(j))
    			return false;
    		i++;
    		j--;
    	}
    	return true;
    }
}
