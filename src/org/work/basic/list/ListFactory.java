package org.work.basic.list;

public class ListFactory {
	public static ListNode getList(int n){
		ListNode dummy = new ListNode(-1);
		ListNode p = dummy;
		for(int i = 0; i < n; i++){
			p.next = new ListNode(i);
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
