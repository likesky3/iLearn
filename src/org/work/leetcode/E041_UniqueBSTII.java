package org.work.leetcode;

import java.util.ArrayList;

public class E041_UniqueBSTII {
	 public ArrayList<TreeNode> generateTrees(int n) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        return generateTrees(1, n);
	    }
	    
	    private ArrayList<TreeNode> generateTrees(int start, int end){
	        ArrayList<TreeNode> res = new ArrayList<TreeNode>();
	        
	        //the following 3 lines are important!
	        if(start > end){
	            res.add(null);
	            return res;
	        }        
	        /*if(start == end){
	            res.add(new TreeNode(start));
	            return res;
	        }*/
	            
	        for(int i = start; i <= end; i++){
	            for(TreeNode left : generateTrees(start, i - 1)){
	                for(TreeNode right : generateTrees(i + 1, end)){
	                    TreeNode root = new TreeNode(i);
	                    root.left = left;
	                    root.right = right;
	                    res.add(root);
	                }
	            }
	        }
	        return res;
	    }
}
