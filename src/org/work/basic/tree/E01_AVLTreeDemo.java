package org.work.basic.tree;

public class E01_AVLTreeDemo {
	
	public AVLTree singleRotateLeft(AVLTree k2){
		AVLTree k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		
		k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
		k1.height = Math.max(getHeight(k1.left), k2.height) + 1;
		return k1;
	}
	
	public AVLTree singleRotateRight(AVLTree k2){
		AVLTree k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		
		k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
		k1.height = Math.max(k2.height, getHeight(k1.right)) + 1;
		return k1;
	}

	//L-R, k1 is k3.left, k2 is k1.right
	public AVLTree doubleRotateLeft(AVLTree k3){
		//rotate between k1 and k2
		k3.left = singleRotateRight(k3.left);
		
		//rotate between k3 and k2
		return singleRotateLeft(k3);
	}
	public int getHeight(AVLTree node){
		if(node == null)
			return -1;
		else
			return node.height;
	}
}

class AVLTree{
	public int value;
	public AVLTree left;
	public AVLTree right;
	public int height;
	public AVLTree(int value){
		this.value = value;
		left = null;
		right = null;
		height = 0;
	}
}
