package org.work.leetcode;

import java.util.LinkedList;

public class E053_ReversedLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode node = new ListNode(-1);
		ListNode node2 = new ListNode(-3);
		node.next = node2;
		E053_ReversedLinkedList obj = new E053_ReversedLinkedList();
		ListNode item = obj.reverseBetween(node, 1, 2);
		while(item != null){
			System.out.println(item.val);
			item = item.next;
		}
	}
	
	//use stack, not in-place
	public ListNode reverseBetween2(ListNode head, int m, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(m >= n)
            return head;
        
        LinkedList<ListNode> s = new LinkedList<ListNode>();

        int i = 0;
        ListNode pre = null;
        //initial value of pre: null or head
        if(m > 1){
            pre = head;
            i++;
        }
        while(i < m - 1){
            pre = pre.next;
            i++;
        }
        
        ListNode tail = null;
        //initial value of tail: node @m
        if(m == 1){
            tail = head;
            i++;
        }else
            tail = pre.next;
            
        while(i <= n){
            s.push(tail);
            tail = tail.next;
            i++;
        }
        
        ListNode curr = null;
        if(pre == null){
        	curr = s.pop();
        	head = curr;
        }
        else {
			curr = pre;
		}
        while(!s.isEmpty()){
            curr.next = s.pop();
            curr = curr.next;
        }
        curr.next = tail;
        
        return head;
        
    }
	
	//others, in-place
	//1-2-3-4-5, m = 2, n = 4
	//round 1: [1] [3-2] [4-5]
	//round 2: [1] [4-3-2] [5]
	//rest: 1-4-3-2-5
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if(m >= n)
			return head;
		
		ListNode q = null;
		ListNode p = head;
		for(int i = 1; i <= m - 1; i++){
			q = p;
			p = p.next;
		}
		
		ListNode end = p;
		ListNode pPre = p;
		p = p.next;
		for(int i = m + 1; i <= n; i++){
			ListNode pNext = p.next;
			p.next = pPre;
			pPre = p;
			p = pNext;
		}
		
		end.next = p;
		if(q == null)
			head = pPre;
		else {
			q.next = pPre;
		}
		
		return head;
	}

}
