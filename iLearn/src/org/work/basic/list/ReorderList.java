package org.work.basic.list;

public class ReorderList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReorderList obj = new ReorderList();
		obj.reorderList(ListFactory.getList(5));
		
	}

	public void reorderList(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(head == null || head.next == null)
            return;
        
        //find middle node    
        ListNode slow = findMid(head);
        
        //reverse the second half
        ListNode second = new ListNode(-1);
        second.next = slow.next;
        slow.next = null;
        ListNode last = null, curr = null;
        while(curr != null){
            last.next = curr.next;
            curr.next = last;
            second.next = curr;
            curr = last.next;
        }
        ListFactory.print(head);
        ListFactory.print(second);
        
        //insert the second half into the first half
        ListNode p1 = head;
        ListNode p2 = second.next;
        while(p2 != null){
            p2.next = p1.next;
            p1.next = p2;
            p2 = p2.next;
            p1 = p1.next;
        }
        
    }
	
	public ListNode findMid(ListNode head){
		ListNode slow = head, fast = head;
        while(fast.next != null){
            fast = fast.next;
            if(fast.next != null){
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
	}
}
