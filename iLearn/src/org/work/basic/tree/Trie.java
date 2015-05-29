package org.work.basic.tree;

class TrieNode {
    public static final int ALPHABET_SIZE = 26;
    // Initialize your data structure here.
    TrieNode[] children;
    public boolean isAWord = false;
    public TrieNode() {
        children = new TrieNode[ALPHABET_SIZE];
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.length() == 0)
            return;
        int n = word.length();
        TrieNode p = root;
        for (int i = 0; i < n; i++) {
            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new TrieNode();
            }
            p = p.children[idx];
        }
        p.isAWord = true;
    }
    
    // Returns if the word is in the trie.
//    public boolean search(String word) {
//        if (word == null || word.length() == 0)
//            return false;
//        int n = word.length();
//        TrieNode p = root;
//        for (int i = 0; i < n; i++) {
//            int idx = word.charAt(i) - 'a';
//            if (p.children[idx] == null)
//                return false;
//            p = p.children[idx];
//        }
//        return p.isAWord;
//    }
    
    // special attention: 待搜索的字符串的最后一个字符在trie树中的节点标记是一个单词时才能认为字典中包含这个单词
    public boolean search(String word, int pos, TrieNode p) {
        if (pos == word.length()) {
            return p.isAWord;
        }
        if (p == null)
            return false;
            
        char currChar = word.charAt(pos);
        if (currChar != '.') {
            int idx = currChar - 'a';
            if (p.children[idx] == null)
                return false;
            p = p.children[idx];
            if (pos == word.length() - 1)
                return p.isAWord;
            return search(word, pos + 1, p);
        } else {
            for (int i = 0; i < TrieNode.ALPHABET_SIZE; i++) {
                if (p.children[i] != null && search(word, pos + 1, p.children[i])) {
                    return true;
                }
            }
            return false;
        }
    }
    
    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null || word.length() == 0)
            return false;
       return search(word, 0, root);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        int n = prefix.length();
        TrieNode p = root;
        for (int i = 0; i < n; i++) {
            int idx = prefix.charAt(i) - 'a';
            if (p.children[idx] == null)
                return false;
            p = p.children[idx];
        }
        return true;
    }
    public static void main(String[] args) {
        // insert("abc"),search("abc"),search("ab"),insert("ab"),search("ab"),insert("ab"),search("ab")
        Trie trietree = new Trie();
        trietree.insert("abc");
        trietree.insert("ab");
        System.out.println(trietree.search("ab"));
        trietree.insert("ab");
        System.out.println(trietree.search("ab"));
    }
}
