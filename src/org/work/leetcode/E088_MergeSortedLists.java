package org.work.leetcode;

public class E088_MergeSortedLists{
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        
        ListNode tmp = null;
            
        if(l1.val > l2.val){    
            tmp = l2;
            l2 = l1;
            l1 = tmp;
        }
            
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode merged = l1;
        
        
        while(p1 != null & p2 != null){
            if(p1.val <= p2.val){
                merged = p1;
                p1 = p1.next;
            }
            else{
                merged.next = p2;
                merged = p2;
                tmp = p2.next;
                p2.next = p1;
                p2 = tmp;
                
            }
            
        }
        
        if(p2 != null)
            merged.next = p2;
            
        return l1;
        
    }
}
