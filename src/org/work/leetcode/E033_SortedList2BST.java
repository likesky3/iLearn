package org.work.leetcode;

import java.util.ArrayList;

public class E033_SortedList2BST {
	public static void main(String[] args){
//		ListNode node = new ListNode(0);
//		SortedList2BST_33 instance = new SortedList2BST_33();
//		TreeNode rootNode = instance.sortedListToBST(node);
		
	}
	
	public TreeNode sortedListToBST(ListNode head) {
		if(head == null)
			return null;
		
		//transform the list to array and apply array method
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(head != null){
			list.add(head.val);
			head = head.next;
		}
		
		Integer[] num = list.toArray(new Integer[0]); //notice how to transfer list to array
		return process(num, 0, num.length - 1);
		
	}
	
	private TreeNode process(Integer[] num, int from, int to){
		if(from > to)
			return null;
		int mid = (from + to) / 2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = process(num, from, mid - 1);
		root.right = process(num, mid + 1, to);
		return root;
	}
	
	
}
