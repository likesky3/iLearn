package org.work.weiss.chap3;

import java.util.HashSet;

import org.work.basic.list.ListNode;

public class Exe34_DoesListContainsCycle {

	public static void main(String[] args) {
		ListNode tail = new ListNode(3,null);
		ListNode head = new ListNode(2, tail);
		head = new ListNode(1, head);
		System.out.println(Exe34_DoesListContainsCycle.hasCycle1(head));
		System.out.println(Exe34_DoesListContainsCycle.hasCycle2(head));
		tail.next = head;
		System.out.println(Exe34_DoesListContainsCycle.hasCycle1(head));
		System.out.println(Exe34_DoesListContainsCycle.hasCycle2(head));
	}
	
	
	/**method1: use a hashset to store the visited node,
	 * if a new node is alread in the hashset, the list contains cycle
	 * and the first duplicated node found is the entrance node of the cycle
	 * cost: o(n) + o(n) 
	 */
	public static boolean hasCycle1(ListNode list) {
		HashSet<ListNode> set = new HashSet<>();
		ListNode curr = list;
		while(curr != null) {
			if (set.contains(curr))
				return true;
			set.add(curr);
			curr = curr.next;
		}
		return false;
	}
	
	/**method2: use slow-fast pointer trick, if the list contains cycle, 
	 * the two pointer will meet each other at last
	 * cost: o(n) + o(1)
	 */
	public static boolean hasCycle2(ListNode list) {
		if (list == null || list.next == null || list.next.next == null)
			return false;
		ListNode p1 = list.next;
		ListNode p2 = p1.next;
		while (p2 != p1) {
			if (p2.next == null)
				return false;
			p2 = p2.next;
			if (p2.next == null)
				return false;
			p2 = p2.next;
			p1 = p1.next;
		}
		return true;
	}

}
