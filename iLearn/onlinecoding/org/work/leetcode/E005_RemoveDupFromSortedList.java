package org.work.leetcode;


public class E005_RemoveDupFromSortedList {
	public ListNode deleteDuplicates(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(head == null || head.next == null)
            return head;
        
        ListNode lastNode = head;
        ListNode currNode = head.next;
        boolean isNext = true;
        
        while(currNode != null){
            if(currNode.val != lastNode.val){
                if(!isNext)
                    lastNode.next = currNode;
                lastNode = currNode;
                isNext = true;
            }else if(isNext){
                isNext = false;
            }
            
            currNode = currNode.next;
        }
        lastNode.next = currNode; //important! {1,1}
        
        return head;
    }
}
