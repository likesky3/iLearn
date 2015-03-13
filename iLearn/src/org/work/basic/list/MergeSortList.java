package org.work.basic.list;

import java.util.LinkedList;

public class MergeSortList {
    //1.if head == null || head.next == null,return
    //2. frontBackSplit(head, a, b)
    //3. mergeSort(a), mergeSort(b)
    //4. head = sortedMerge(a, b)
	public static void main(String[] args){
		ListNode head = new ListNode(5);
		ListNode n1 = new ListNode(3);
		ListNode n2 = new ListNode(6);
		ListNode n3 = new ListNode(2);
		head.next = n1;
		n1.next = n2;
		n2.next = n3;
		MergeSortList obj = new MergeSortList();
		obj.mergeSort(head);
//		while(head != null){
//			System.out.println(head.val);
//			head = head.next;
//		}
//		LinkedList list =new LinkedList();
//		list.add(5);
//		list.add(3);
//		list.add(6);
//		list.add(2);
		
	}
	
    public void mergeSort(ListNode head){
        if(head == null || head.next == null)
            return;
            
        ListNode a = new ListNode(), b = new ListNode();
        splitList(head, a, b);
        mergeSort(a.next);
        mergeSort(b.next);
        head = mergeList(a.next, b.next);
//        while(head != null){
//			System.out.println(head.val);
//			head = head.next;
//		}
//        System.out.println("---");
    }
    
    private void splitList(ListNode source, ListNode front, ListNode back){
        if(source == null || source.next == null){
            front = source;
            back = source.next;
        }
        
        ListNode slow = source;
        ListNode fast = source.next;
        
        //if check fast.next, back list will have one more node if total is odd
        //if check fast, front list will be longer if total is odd
        while(fast.next != null){
            fast = fast.next;
            while(fast.next != null){
                fast = fast.next;
                slow = slow.next;
            }
        }
        front.next = source;
        back.next = slow.next;
        slow.next = null;
    }
    
    public ListNode mergeList(ListNode a, ListNode b){
        ListNode result = new ListNode();
        result.next = null;
        ListNode p = result;
        
        while(true){
            if(a == null){
                p.next = b;
                break;
            }else if(b == null){
                p.next = a;
                break;
            }else if(a.val <= b.val){
                p.next = a;
                a = a.next;
            }else{
                p.next = b;
                b = b.next;
            }
            p = p.next;
        }
        
        return result.next;
    }
}
