package org.work.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class E029_LevelOrderBottomUp {
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
		
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(root == null)
			return res;
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		ArrayList<Integer> levelCounters = new ArrayList<Integer>();
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		
		queue.offer(root);
		int nextLevelCounter = 1;
		
		while(!queue.isEmpty()){
			int currLevelCounter = nextLevelCounter;
			levelCounters.add(currLevelCounter);
			nextLevelCounter = 0;
			
			for(int i = 0; i < currLevelCounter; i++){
				TreeNode node = queue.poll();
				list.add(node);
				if(node.left != null){
					queue.offer(node.left);
					nextLevelCounter++;
				}
				if(node.right != null){
					queue.offer(node.right);
					nextLevelCounter++;
				}
			}
		}
		
		int remains = list.size();
		for(int i = levelCounters.size() - 1; i >= 0; i--){
			int currLevelCounter = levelCounters.get(i);
			remains -= currLevelCounter;
			int startIndex = remains;
			ArrayList<Integer> level = new ArrayList<Integer>();
			for(int j = startIndex; j < startIndex + currLevelCounter; j++){
				level.add(list.get(j).val);
			}
			res.add(level);
		}
		
		return res;
    }

}
