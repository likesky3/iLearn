package org.work.leetcode;

public class ListNode {
	public int val;
	public ListNode next;
	public ListNode(int val) {
		this.val = val;
		this.next = null;
	} 
	
	public String toString(){
		ListNode ptr = new ListNode(val);
		ptr.next = next;
		StringBuffer res = new StringBuffer();
		while(ptr != null){
			res.append(ptr.val);
			res.append(", ");
			ptr = ptr.next;
		}
		return res.toString();
	}
}
