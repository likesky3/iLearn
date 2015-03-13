package org.work.leetcode;

//3 pointers
public class E066_SwapNodesInPairs {
	public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
            
        ListNode p = head;
        ListNode q = head.next;
        ListNode pPre = null;
        boolean isOldHead = true;
        
        while(p != null && q != null){
            
            p.next = q.next;
            q.next = p;
            if(isOldHead){
                head = q;
                isOldHead = false;
            }
            if(pPre != null)
                pPre.next = q;
            
            pPre = p;
            p = p.next;
            if(p != null)
                q = p.next;
        }
        return head;
        
    }
}
