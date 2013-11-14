package org.work.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;


public class IterativePostN_OrderTree {
	
	public static void main(String[] args){
		NTreeNode root = new NTreeNode(0);
		NTreeNode node1 = new NTreeNode(1);
		NTreeNode node2 = new NTreeNode(2);
		NTreeNode node3 = new NTreeNode(3);
		NTreeNode node4 = new NTreeNode(4);
		NTreeNode node5 = new NTreeNode(5);
		NTreeNode node6 = new NTreeNode(6);
		NTreeNode node7 = new NTreeNode(7);
		NTreeNode node8 = new NTreeNode(8);
		root.addChild(node1);
		root.addChild(node2);
		root.addChild(node3);
		node1.addChild(node4);
		node1.addChild(node5);
		node1.addChild(node6);
		node3.addChild(node7);
		node3.addChild(node8);
		postN_orderTree(root);
		
	}
	
	public static void postN_orderTree(NTreeNode root){
		if(root == null)
			return;
		
		LinkedList<NTreeNode> stack = new LinkedList<NTreeNode>();
		stack.push(root);
		NTreeNode curr = root;
		NTreeNode prev = null;
		
		while(!stack.isEmpty()){
			curr = stack.peek();
			
			if(curr.children.isEmpty()){//leaf node
				prev = stack.pop();
				System.out.println(prev.val);
			}
			else if(prev == null || !isLastChild(prev, curr) ){
				for(int i = curr.children.size() - 1;  i >= 0; i--){
					stack.push(curr.children.get(i));
				}
			}else{//children have been visited
				prev = stack.pop();
				System.out.println(prev.val);
			}
		}
	}
	
	private static boolean isLastChild(NTreeNode node, NTreeNode parent){
		return node == parent.children.get(parent.children.size() - 1);
	}
	
}

class NTreeNode{
//	int childrenNum;
	ArrayList<NTreeNode> children;
	int val;
	public NTreeNode(int value){
		val = value;
		children = new ArrayList<NTreeNode>();
//		childrenNum = 0;
	}
	
	public void addChild(NTreeNode node){
		children.add(node);
//		childrenNum = children.size();
	}
}