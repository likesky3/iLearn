package org.work.beautyofp;

import java.util.ArrayList;

import org.work.leetcode.ListNode;

//if the given 2 lists does intersect, return true;
//else return false
public class E36_IntersectionOf2Lists {
	public static void main(String[] args){
		ListNode h1 = new ListNode(1);
		ListNode h2 = new ListNode(2);
		ListNode n1 = new ListNode(3);
		ListNode n2 = new ListNode(4);
		h1.next = n1;
		n1.next = n2;
		h2.next = n2;
		
		E36_IntersectionOf2Lists obj = new E36_IntersectionOf2Lists();
		boolean res = obj.checkIntersection2(h1, h2);
		System.out.println(res);
	}
	
	//Time: O(list1.size() + list2.size()), Space: O(list1.size() + list2.size())
	//if intersect, the last node must be the same
	private boolean checkIntersection(ListNode h1, ListNode h2){
		ArrayList<ListNode> a1 = new ArrayList<ListNode>();
		ArrayList<ListNode> a2 = new ArrayList<ListNode>();
		
		ListNode ptr = h1;
		while(ptr != null){
			a1.add(ptr);
			ptr = ptr.next;
		}
		
		ptr = h2;
		while(ptr != null){
			a2.add(ptr);
			ptr = ptr.next;
		}
		
		if(a1.get(a1.size() - 1) == a2.get(a2.size() - 1))
			return true;
		else
			return false;
	}
	
	//Time: O(list1.size() + list2.size()), Space: none
	//if intersect, the last node must be the same
	private boolean checkIntersection2(ListNode h1, ListNode h2){
		ListNode ptr = h1;
		while(ptr.next != null)
			ptr = ptr.next;
		
		ListNode ptr2 = h2;
		while(ptr2 != null){
			if(ptr == ptr2)
				return true;
			ptr2 = ptr2.next;
		}
		return false;
		
	}
}
