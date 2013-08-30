package org.work.leetcode;

import java.util.ArrayList;

public class E086_MergeKLists {
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if(lists == null || lists.isEmpty())
			return null;
		ListNode l1 = lists.get(0);
		for(int i = 1; i < lists.size(); i++){
			l1 = merge2Lists(l1, lists.get(i));
		}
		return l1;
	}
	
	public ListNode merge2Lists(ListNode list1, ListNode list2){
		if(list1 == null)
			return list2;
		if(list2 == null)
			return list1;
		
		ListNode tmp;
		if(list1.val > list2.val){
			tmp = list1;
			list1 = list2;
			list2 = tmp;
		}
		
		ListNode p1 = list1.next;
		ListNode p2 = list2;
		ListNode merged = list1;
		while(p1 != null && p2 != null){
			if(p1.val <= p2.val){
				merged = p1;
				p1 = p1.next;
			}else{
				tmp = p2.next;
				p2.next = p1;
				merged.next = p2;
				merged = p2;
				p2 = tmp;
			}
		}
		if(p2 != null)
			merged.next = p2;
		return list1;
	}
}
