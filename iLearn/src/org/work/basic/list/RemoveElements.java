package org.work.basic.list;

public class RemoveElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val != val) {
                prev = curr;
                curr = curr.next;
            } else {
                if (prev != null) {
                    prev.next = curr.next;
                    curr = curr.next;
                } else {
                    curr = curr.next;
                    head = curr;
                }
            }
        }
        return head;
    }
}
