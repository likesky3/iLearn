package org.work.leetcode;

import java.util.ArrayList;
import java.util.Stack;

public class E040_BSTInorder {
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
            
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
                //res.add(curr.val);
            }else{
                curr = stack.pop();
                res.add(curr.val);//only add value popped out from stack
                curr = curr.right;
            }
        }
        return res;
    }
}
