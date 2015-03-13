package org.work.basic.list;

public class ListNode {
	public ListNode() {
	}

	public ListNode(int val) {
		this.val = val;
	}
	
	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	public int val;
	public ListNode next;
}
