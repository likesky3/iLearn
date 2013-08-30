package org.work.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//lever-order traversal, single queue with 2 conunters
public class E028_LevelOrderUpDown {
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(root == null)
			return res;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		int nextLevelCounter = 1;
		queue.offer(root);
		
		while(!queue.isEmpty()){
			int currLevelCounter = nextLevelCounter;
			nextLevelCounter = 0;
			
			ArrayList<Integer> level = new ArrayList<Integer>();
			for(int i = 0; i < currLevelCounter; i++){
				TreeNode node = queue.poll();
				level.add(node.val);
				if(node.left != null){
					queue.offer(node.left);
					nextLevelCounter++;
				}
				if(node.right != null){
					queue.offer(node.right);
					nextLevelCounter++;
				}
			}
			res.add(level);
		}
		
		return res;
	}

	
}
