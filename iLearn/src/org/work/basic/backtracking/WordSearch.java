package org.work.basic.backtracking;

public class WordSearch {
    public static void main(String[] args) {
        WordSearch obj = new WordSearch();
        char[][] board = new char[31][31];
        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 31; j++) {
                board[i][j] = 'a';
            }
        }
        board[30][30] = 'b';
        StringBuilder word = new StringBuilder(1000);
        word.append('b');
        for (int i = 0; i < 900; i++)
            word.append('a');
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            boolean ret = obj.exist(board, word.toString());
        }
        System.out.println(System.currentTimeMillis() - start );
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
        boolean ret = obj.exist1(board, word.toString());
        }
        System.out.println(System.currentTimeMillis() - start );
    }
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null || word.length() == 0)
            return false;
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] used = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (recur(board, i, j, word, 0, used))
                    return true;
            }
        }
        return false;
    }
    private boolean recur(char[][] board, int i, int j, String word, int curr, boolean[][] used) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || used[i][j])
            return false;
        if (curr == word.length())
            return true;
        if (board[i][j] == word.charAt(curr)) {
            used[i][j] = true;
            boolean ret = recur(board, i, j + 1, word, curr + 1, used)
                            || recur(board, i, j - 1, word, curr + 1, used)
                            || recur(board, i + 1, j, word, curr + 1, used)
                            || recur(board, i - 1, j, word, curr + 1, used);
            used[i][j] = false;
            return ret;
        } else {
            return false;
        }
    }
   
    public boolean exist1(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 
            || word == null || word.length() == 0)
            return false;
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] used = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, word, 0, i, j, used))
                    return true;
            }
        }
        return false;
    }
    public boolean dfs(char[][] board, String word, int idx, int i, int j, boolean[][] used) {
        if (idx == word.length())
            return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || used[i][j] || board[i][j] != word.charAt(idx))
            return false;
        used[i][j] = true;
        boolean result = dfs(board, word, idx + 1, i, j + 1, used)
                        || dfs(board, word, idx + 1, i, j - 1, used)
                        || dfs(board, word, idx + 1, i + 1, j, used)
                        || dfs(board, word, idx + 1, i - 1, j, used);
        used[i][j] = false;
        return result;
    }
}