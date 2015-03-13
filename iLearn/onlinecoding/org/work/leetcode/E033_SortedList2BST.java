package org.work.leetcode;

import java.util.ArrayList;

public class E033_SortedList2BST {
	public static void main(String[] args){
//		ListNode node = new ListNode(0);
//		SortedList2BST_33 instance = new SortedList2BST_33();
//		TreeNode rootNode = instance.sortedListToBST(node);
		
	}
	
	//version 1, list -> array
	public TreeNode sortedListToBST1(ListNode head) {
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
	
	//O(N), bottom up, in list order
	private ListNode curr;
	public TreeNode sortedListToBST(ListNode head){
		if(head == null)
			return null;
		curr = head;
		int len = 1;
		ListNode p = head;
		while(p.next != null){
			len++;
			p = p.next;
		}
		return build(0, len - 1);
	}
	
	private TreeNode build(int start, int end){
		if(start > end)
			return null;
		
		int mid = start + (end - start) / 2; //avoid overflow;
		TreeNode left = build(start, mid - 1);
		
		TreeNode root = new TreeNode(curr.val);
		curr = curr.next;
		
		TreeNode right = build(mid +1, end);
		
		root.left = left;
		root.right = right;
		return root;
	}
	
	
}
