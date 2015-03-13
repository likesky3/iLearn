package org.work.basic.list;

public class InsertionSortList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode node1 = new ListNode(4);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(1);
		ListNode node4 = new ListNode(3);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		ListNode ret = InsertionSortList.insertionSortList(node1);
		ListFactory.print(ret);
	}

	public  static ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode last = head;
        ListNode curr = last.next;
        while(curr != null){
//        	System.out.println(curr.val);
            if(curr.val < head.val){
                last.next = curr.next;
                curr.next = head;
                head = curr;
            }else{
                ListNode prev = head;
                ListNode cand = head.next;
                while(prev != last && cand.val <= curr.val){
                    prev = cand;
                    cand = cand.next;
//                    System.out.println("cand: " + cand.val);
                }
                if(prev == last){
                    last = curr;
                }else{
                    last.next = curr.next;
                    curr.next = cand;
                    prev.next = curr;
                }
            }
            curr = last.next;
        }
        return head;
    }
}
