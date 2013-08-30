package org.work.leetcode;

public class E087_ReverseNodesKGroup {
	public static void main(String[] args){
		E087_ReverseNodesKGroup obj = new E087_ReverseNodesKGroup();
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		node1.next = node2;
		node2.next = node3;
		
		ListNode res = obj.reverseKGroup(node1, 2);
		
		while(res != null){
			System.out.println(res.val);
			res = res.next;
		}
//		System.out.println(node1);
	}
	
	public ListNode reverseKGroup(ListNode head, int k) {
		if(head == null || k < 2)
			return head;
		
		//step 1: get the length of the list
		int length = 0;
		ListNode ptr = head;
		while(ptr != null){
			length++;
			ptr = ptr.next;
		}
		//if length < k, return the list as it was.
		if(length < k)
			return head;
		
		//step 2: reverse in k-Group
		int remains = length;
		ListNode pPre = null;
		ListNode p = head;
		
		
		ListNode kStart = null;
		ListNode kEnd = null;
		ListNode lastGroupEnd = kEnd;
		boolean headReset = false;
		int counter = k;
		while(remains >= k){
			pPre = p;
			kEnd = pPre;//kEnd points to the end node of current k-Group
			if(p != null)
				p = p.next;
			//2.1 reverse nodes in the current k-Group
			while(counter > 1){
				ListNode pNext = p.next;
				p.next = pPre;
				pPre = p;
				p = pNext;
				counter--;
			}
			kStart = pPre;//kStart points to the start node of current k-Group
			
			//get new header node
			if(!headReset){
				head = kStart;
				headReset = true;
				kStart = null;
			}
				
			//2.2 link the current k-Group to next k-Group
			if(kStart != null)
				lastGroupEnd.next =kStart; 
			lastGroupEnd = kEnd;
			
			//2.3 move to the next k-Group
			remains -= k;
			counter = k;
		}
		if(remains > 0){
			lastGroupEnd.next = p;
		}else{
			if(lastGroupEnd != null)
				lastGroupEnd.next = null;
		}
		
		return head;
	}
	
}
