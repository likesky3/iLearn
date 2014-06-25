package org.work.basic.list;

import java.util.Random;

public class ListFactory {
	public static ListNode getList(int n){
		ListNode dummy = new ListNode(-1);
		ListNode p = dummy;
		Random rand = new Random();
		for(int i = 0; i < n; i++){
			p.next = new ListNode(rand.nextInt(20));
			p = p.next;
		}
		return dummy.next;
	}
	
	public static void print(ListNode head){
		ListNode p = head;
		int c = 0;
		while(c < 20 &&p != null){
			System.out.print(p.val + "\t");
			p = p.next;
			c++;
		}
		System.out.println();
	}
	
	
}
