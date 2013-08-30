package org.work.beautyofp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.work.leetcode.ListNode;

//if the given 2 lists does intersect, return the first node
//where intersect occurs

public class E36_IntersectionOf2ListsII {
	public static void main(String[] args){
		ListNode h1 = new ListNode(1);
		ListNode h2 = new ListNode(2);
		ListNode n1 = new ListNode(3);
		ListNode n2 = new ListNode(4);
		h1.next = n1;
		n1.next = n2;
		h2.next = n1;
		
		E36_IntersectionOf2ListsII obj = new E36_IntersectionOf2ListsII();
		ListNode node = obj.findFirstComNode2(h1, h2);
		if(node != null)
			System.out.println(node.val);
		else {
			System.out.println("no intersection");
		}
	}
	
	//Time: O(list1.size() + list2.size()), Space: O(list1.size() + list2.size())
	//search from end to start
	private ListNode findFirstComNode(ListNode h1, ListNode h2){
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
		
		int i1 = a1.size();
		int i2 = a2.size();
		while(i1 >= 1 && i2 >= 1){
			if(a1.get(i1 - 1) != a2.get(i2 - 1))
				break;
			
			i1--;
			i2--;
		}
		if(i1 == a1.size())
			return null;
		else 
			return a1.get(i1);
		
	}
	
	//Time: O(list1.size() + list2.size()), use hashmap
	private ListNode findFirstComNode2(ListNode h1, ListNode h2){
		HashMap<ListNode, Integer> map = new HashMap<ListNode, Integer>();
		
		ListNode ptr1 = h1;
		int i = 0;
		while(ptr1 != null){
			map.put(ptr1,i++);
			ptr1 = ptr1.next;
		}
		
		ListNode ptr2 = h2;
		while(ptr2 != null){
			if(map.containsKey(ptr2))
				return ptr2;
			ptr2 = ptr2.next;
		}
		return null;
	}
}
