/**
 * PopulatingNext2RightPointers_18.java
 * 
 * Copyright (c) 2011 State Street Bank and Trust Corp. 225 Franklin Street, Boston, MA
 * 02110, U.S.A. All rights reserved.
 * 
 * PopulatingNext2RightPointers_18.java is the copyrighted, proprietary property of State
 * Street Bank and Trust Company and its subsidiaries and affiliates which retain all
 * right, title and interest therein."
 * 
 * Revision History
 * 
 * Date Programmer Notes ------------ -------------------- ----------------------------
 * May 23, 2013 a524690 Init
 * 
 */
package org.work.leetcode;

/**
 * @author a524690 the given tree could be any binary tree 
 * 		efficiency in Leetcode:
 *      V1 < V2 < V3 递归算法非常适用于tree这种数据结构
 */
public class E019_PopulatingNext2RightPointers {
	public static void main(String[] args) {
		TreeLinkNode node1 = new TreeLinkNode(1);
		TreeLinkNode node2 = new TreeLinkNode(2);
		TreeLinkNode node3 = new TreeLinkNode(3);
		TreeLinkNode node4 = new TreeLinkNode(4);
		TreeLinkNode node5 = new TreeLinkNode(5);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node3.right = node5;

		E019_PopulatingNext2RightPointers.connectV3(node1);
		System.out.println("Done");

	}

	public static void connectV1(TreeLinkNode root) {
		if (root == null || (root.left == null && root.right == null))
			return;

		// head has at least one child
		TreeLinkNode head = root;
		TreeLinkNode rear = root;
		TreeLinkNode next = null;
		TreeLinkNode nonLeafNext = null;

		// ends when rear reaches leaf level
		while (rear.left != null || rear.right != null) {
			// part1: find non-leaf rear.next and assign non-null child of it to
			// next
			nonLeafNext = rear.next;
			while (nonLeafNext != null && nonLeafNext.left == null
					&& nonLeafNext.right == null)
				nonLeafNext = nonLeafNext.next;
			if (nonLeafNext != null) {
				if (nonLeafNext.left != null)
					next = nonLeafNext.left;
				else {
					next = nonLeafNext.right;
				}
			} else {
				next = null;
			}

			// part2:assign next ptr of child nodes
			if (rear.left != null) {// assign next ptr of left node

				if (rear.right != null) {// assign next ptr of right node
					rear.left.next = rear.right;
					rear.right.next = next;
				} else {
					rear.left.next = next;
				}
			} else if (rear.right != null)
				rear.right.next = next;

			// part3:update rear node so as to continue the iteration
			if (rear.next == null) {// ends work of one layer, update rear &
									// head ptrs and go to next layer
				head = rear = goToNextLayer(head, rear);
				if (rear == null)
					break;
			} else {
				// update rear ptr and go to next non-leaf node
				while (rear.next != null && rear.next.left == null
						&& rear.next.right == null)
					rear = rear.next;
				if (rear.next == null) // all the rest nodes in this layer are
										// leaf node,
				// go to next layer
				{
					head = rear = goToNextLayer(head, rear);
					if (rear == null)
						break;
				} else {
					rear = rear.next;
				}
			}
		}
	}

	private static TreeLinkNode goToNextLayer(TreeLinkNode head,
			TreeLinkNode rear) {
		// 老的head至少包含一个child node,首先设置rear为下一层中第一个非null节点
		if (head.left != null)
			rear = head.left;
		else if (head.right != null)
			rear = head.right;

		// 接着在新层中 寻找至少包含一个child node的，并赋值给rear & head
		while (rear != null && rear.left == null && rear.right == null)
			rear = rear.next;
		head = rear;
		return head;
	}

	public static void connectV3(TreeLinkNode root) {
		if (root == null)
			return;
		
		//part 1
		TreeLinkNode ptr = root.next;
		while (ptr != null) {
			if (ptr.left != null) {
				ptr = ptr.left;
				break;
			}
			if (ptr.right != null) {
				ptr = ptr.right;
				break;
			}
			ptr = ptr.next;
		}
		
		//part 2
		if (root.right != null) {
			root.right.next = ptr;
		}
		if (root.left != null) {
			root.left.next = ((root.right == null) ? ptr : root.right);
		}

		connectV3(root.right);
		connectV3(root.left);
	}
	
	//improve code in part1
	public static void connectV4(TreeLinkNode root) {
		if (root == null)
			return;
		
		//part 1
		TreeLinkNode up = root.next;
		TreeLinkNode next = null;
		while(up != null){
			next = (up.left != null ? up.left : up.right);
			if(next != null)
				break;
			else
				up = up.next;
		}
		
		//part 2
		if (root.right != null) {
			root.right.next = next;
		}
		if (root.left != null) {
			root.left.next = ((root.right == null) ? next : root.right);
		}

		connectV4(root.right);
		connectV4(root.left);
		
	}

}
