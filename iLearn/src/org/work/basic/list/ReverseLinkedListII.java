package org.work.basic.list;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(m == n)
            return head;
            
        ListNode pBefore = new ListNode(-1);
        pBefore.next = head;
        ListNode prev, curr = head,next;
        
        int c = 1;
        while(c < m){
            pBefore = pBefore.next;
            c++;
        }
        
        ListNode mNode = pBefore.next;
        prev = pBefore.next;
        curr = prev.next;
        while(c < n){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            c++;
        }
        pBefore.next = prev;
        mNode.next = curr;
        
        return mNode == head ? pBefore.next : head;
    }
}
