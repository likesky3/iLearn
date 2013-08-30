package org.work.leetcode;

import java.util.ArrayList;

public class E063_RemoveNthFromEnd {

	//version 1, time & space: O(N)
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if(n == 0)
			return head;
		
		ArrayList<ListNode> array = new ArrayList<ListNode>();
		
		ListNode curr = head;
		while(curr != null){
			array.add(curr);
			curr = curr.next;
		}
		
		int pos = array.size() - n;
		if(pos == 0)
			head = head.next;
		else {
			array.get(pos - 1).next = array.get(pos).next;
		}
		
		return head;
	}
	
	//version 2, others, two pointers
	//两个指针相隔n-1，每次两个指针向后一步，当后面一个指针没有后继了，前面一个指针就是要删除的节点。
	public ListNode removeNthFromEnd2(ListNode head, int n) {
		ListNode pPre = null;
		ListNode p = head;
		ListNode q = head;
		
		for(int i = 2; i <= n; i++){
			q = q.next;
		}
		
		while(q.next != null){
			pPre = p;
			p = p.next;
			q = q.next;
		}
		
		if(pPre == null){
			head = p.next;
		}else {
			pPre.next = p.next;
		}
		return head;
	}
}
