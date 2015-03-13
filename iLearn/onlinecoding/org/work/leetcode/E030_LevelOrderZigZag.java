package org.work.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class E030_LevelOrderZigZag {
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return res;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		ArrayList<Integer> levelCounters = new ArrayList<Integer>();
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();

		queue.offer(root);
		int nextLevelCounter = 1;

		while (!queue.isEmpty()) {
			int currLevelCounter = nextLevelCounter;
			levelCounters.add(currLevelCounter);
			nextLevelCounter = 0;

			for (int i = 0; i < currLevelCounter; i++) {
				TreeNode node = queue.poll();
				list.add(node);
				if (node.left != null) {
					queue.offer(node.left);
					nextLevelCounter++;
				}
				if (node.right != null) {
					queue.offer(node.right);
					nextLevelCounter++;
				}
			}
		}

		int from = 0;
		boolean left2right = true;
		for (int ilevel = 0; ilevel < levelCounters.size(); ilevel++) {
			int currLevelCounter = levelCounters.get(ilevel);
			ArrayList<Integer> level = new ArrayList<Integer>();
			if (left2right) {
				for (int i = from; i < from + currLevelCounter; i++) {
					level.add(list.get(i).val);
				}
			} else {
				for (int i = from + currLevelCounter - 1; i >= from; i--) {
					level.add(list.get(i).val);
				}

			}
			res.add(level);
			left2right = !left2right;
			from += currLevelCounter;

		}

		return res;
	}
	
	//version 2, neater
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder2(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return res;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();

		queue.offer(root);
		int nextLevelCounter = 1;
		boolean left2right = false;

		while (!queue.isEmpty()) {
			int currLevelCounter = nextLevelCounter;
			left2right = !left2right;
			nextLevelCounter = 0;
			ArrayList<Integer> level = new ArrayList<Integer>();
			
			for (int i = 0; i < currLevelCounter; i++) {
				TreeNode node = queue.poll();
				level.add(node.val);
				if (node.left != null) {
					queue.offer(node.left);
					nextLevelCounter++;
				}
				if (node.right != null) {
					queue.offer(node.right);
					nextLevelCounter++;
				}
			}
			
			if(left2right)
				res.add(level);
			else{
				//reverse elements of level
				for(int i = 0, j = level.size() - 1; i < j; i++,j--){
					int tmp = level.get(j);
					level.set(j, level.get(i));
					level.set(i, tmp);
				}
				res.add(level);
			}
		}

		return res;
	}

}
