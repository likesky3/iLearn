/**
 * PopulatingNext2RightPointers_18.java 
 *
 * Copyright (c) 2011 State Street Bank and Trust Corp.
 * 225 Franklin Street, Boston, MA 02110, U.S.A.
 * All rights reserved.
 *
 * PopulatingNext2RightPointers_18.java is the copyrighted,
 * proprietary property of State Street Bank and Trust Company and its
 * subsidiaries and affiliates which retain all right, title and interest therein."
 * 
 * Revision History
 * 
 * Date            Programmer                Notes
 * ------------    --------------------      ----------------------------
 * May 23, 2013 	   a524690		     Init
 *
 */
package org.work.leetcode;

/**
 * @author a524690
 * assume that the given tree is a perfect binary tree
 *
 */
public class E018_PopulatingNext2RightPointers
{
    public static void main(String[] args)
    {
	TreeLinkNode node1 = new TreeLinkNode(1); 
	TreeLinkNode node2 = new TreeLinkNode(2);
	TreeLinkNode node3 = new TreeLinkNode(3);
	node1.left = node2;
	node1.right = node3;
	
	E018_PopulatingNext2RightPointers.connect(node1);
	System.out.println("Done");
		
    }

    public static void connect(TreeLinkNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null || root.left == null)
            return;
        
        TreeLinkNode head = root;
        TreeLinkNode rear = root;
        
        while(rear.left != null) //ends when rear reaches leaf node
        {
            rear.left.next = rear.right; //assign next ptr of left node
            
            if(rear.next != null)
                rear.right.next = rear.next.left; //assign next ptr of right node
            
            
            if(rear.next == null) //ends work of one layer, update rear & head ptrs and go to next layer
            {
                rear = head.left;
                head = head.left;
                
            }
            else	//update rear ptr and go to next node
                rear = rear.next;

        }
        
    }
}
