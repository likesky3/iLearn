package org.work.basic.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII {

    public static void main(String[] args) {
        WordSearchII obj = new WordSearchII();
        char[][] board = {{'a', 'b'}, {'c', 'd'}};
        String[] words = {"acdb"};
        List<String> result = obj.findWords(board, words);
        System.out.println(result.size());
        List<String> set1 = new ArrayList<String>();
        List<String> set2 = new ArrayList<String>();
        set1.removeAll(set2);
        set1.clear();
    }

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0 
            || words == null || words.length == 0)
            return new ArrayList<String>();
        Trie dict = new Trie();
        for (int i = 0; i < words.length; i++)
            dict.insert(words[i]);
        int rows = board.length;
        int cols = board[0].length;
        Set<String> resultSet = new HashSet<String>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, i, j , new StringBuilder(), new boolean[rows][cols], dict, resultSet);
            }
        }
        return new ArrayList<String>(resultSet);
    }
    public void dfs(char[][] board, int i, int j, StringBuilder curr, boolean[][] used, Trie dict, Set<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || used[i][j])
            return;
        curr.append(board[i][j]);
        String currstr = curr.toString();
        if (!dict.isPrefix(currstr)) {
            curr.deleteCharAt(curr.length() - 1);
            return;    
        }
        if (dict.search(currstr))
            result.add(currstr);
        used[i][j] = true;
        dfs(board, i, j + 1, curr, used, dict, result);
        dfs(board, i, j - 1, curr, used, dict, result);
        dfs(board, i + 1, j, curr, used, dict, result);
        dfs(board, i - 1, j, curr, used, dict, result);
        // »Ö¸´±¾´ÎµÝ¹é³õÊ¼×´Ì¬
        used[i][j] = false;
        curr.deleteCharAt(curr.length() - 1);
    }
}

class TrieNode {
    TrieNode[] children;
    public static final int ALPHABET_NUM = 26;
    boolean isAWord;
    public TrieNode() {
        children = new TrieNode[ALPHABET_NUM];
    }
}

class Trie {
    public TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new TrieNode();
            }
            p = p.children[idx];
        }
        p.isAWord = true;
    }
    public boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null)
                return false;
            p = p.children[idx];
        }
        return p.isAWord;
    }
    public boolean isPrefix(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null)
                return false;
            p = p.children[idx];
        }
        return true;
    }
}
