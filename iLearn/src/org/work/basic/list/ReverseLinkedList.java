package org.work.basic.list;

public class ReverseLinkedList {
    // method 1: 逐个节点逆转
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null)
            return head;
        
        ListNode prev = head;
        ListNode curr = prev.next;
        prev.next = null; // miss this line will lead to daed loop when print the result list
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    // method 2: 依次将后面节点提前
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = head;
        ListNode q = null;
        while (p.next != null) {
            q = p.next;
            p.next = q.next;
            q.next = dummy.next;
            dummy.next = q;
        }
        return dummy.next;
    }
}
