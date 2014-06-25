package org.work.basic.list;


public class ContainsNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode list = ListFactory.getList(10);
		ListNode node = new ListNode(3);
		ListNode node2 = new ListNode(11);
		System.out.println(ContainsNode.contains(node, list));
		System.out.println(ContainsNode.contains(node2, list));
	}
	
	public static boolean contains(ListNode node, ListNode list) {
		if (list == null)
			return false;
		ListNode curr = list;
		while (curr != null) {
			if (curr.val == node.val)
				return true;
			curr = curr.next;
		}
		return false;
	}

}
