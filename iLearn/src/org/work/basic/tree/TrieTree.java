package org.work.basic.tree;

public class TrieTree {
	private final int SIZE = 26;
	private Node root;
	private class Node {
		private boolean isStr; // whether this node is the end node of a string
		private int num; // number of strings which pass this node
		private Node[] children;
		public Node() {
			isStr = false;
			num = 0;
			children = new Node[SIZE];
		}
	}
	
	public TrieTree() {
		root = new Node();
	}
	
	public void insert(String s) {
		if (s == null || s.length() == 0)
			return;
		Node p = root;
		for (int i = 0; i < s.length(); i++) {
			int k = s.charAt(i) - 'a';
			if (p.children[k] == null) {
				Node child = new Node();
				child.num++;
				p.children[k] = child;
			} else {
				p.children[k].num++;
			}
			p = p.children[k];
		}
		p.isStr = true;
	}
	
	// it's not common to delete a string in trie tree
	public void delete(String s) {
	}
	
	public boolean search(String s) {
		if (s == null || s.length() == 0)
			return false;
		Node p = root;
		for (int i = 0; i < s.length(); i++) {
			int k = s.charAt(i) - 'a';
			if (p.children[k] == null) {
				return false;
			} 
			p = p.children[k];
		}
		return p.isStr;
	}
	
	public int countPrefix(String s) {
		if (s == null || s.length() == 0)
			return 0;
		Node p = root;
		for (int i = 0; i < s.length(); i++) {
			int k = s.charAt(i) - 'a';
			if (p.children[k] == null) {
				return 0;
			} 
			p = p.children[k];
		}
		return p.num;
	}
	
	public void printInDictionaryOrder(Node root) {
		Node p = root;
		for (int i = 0; i < SIZE; i++) {
			if (p.children[i] != null) {				
				System.out.print((char)(i + 'a'));
				printInDictionaryOrder(p.children[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}
