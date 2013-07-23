package org.work.leetcode;


public class E006_RemoveDupFromSortedListII {
	public ListNode deleteDuplicates(ListNode head) {
		// Start typing your Java solution below
		// DO NOT write main() function

		if (head == null || head.next == null)
			return head;

		ListNode lastNode = null; //points to node which should be kept in the result list
		
		//if (currNode != targetNode && currNode is next to targetNode)
		//	then this targetNode is the new lastNode
		ListNode targetNode = head; //node under check, 
		ListNode currNode = head.next;
		boolean isNext = true;

		while (currNode != null) {
			if (currNode.val != targetNode.val) {
				if (isNext) {
					if (lastNode == null)
						head = targetNode;
					else
						lastNode.next = targetNode;
					lastNode = targetNode;
				}
				targetNode = currNode;
				isNext = true;
			} else if (isNext) {
				isNext = false;
			}

			currNode = currNode.next;
		}
		
		//善后工作
		if (lastNode == null) {
			if (isNext)
				lastNode = targetNode;
			head = lastNode;
		} else {
			if (isNext)
				lastNode.next = targetNode;
			else
				lastNode.next = null;
		}

		return head;
	}
}
