package org.work.basic.list;

import java.util.Comparator;
import java.util.PriorityQueue;

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
	
	public static void main(String args[]) {
	    PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(new ListNodeComparator());
	    ListNode node1 = new ListNode(12);
	    ListNode node2 = new ListNode(3);
	    heap.add(node1);
	    heap.add(node2);
	    System.out.println(heap.poll().val);
	    System.out.println(heap.poll().val);
	}
}

class ListNodeComparator implements Comparator<ListNode> {
    @Override
    public int compare(ListNode o1, ListNode o2) {
        return o1.val - o2.val;
    }
}