package org.work.basic.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {

    public static void main(String[] args) {
        Set<String> dict = new HashSet<String>();
        dict.add("a");
        dict.add("b");
        dict.add("c");
        WordLadder obj = new WordLadder();
        int a = obj.ladderLength("a", "c", dict);
        System.out.println(a);
        String currWord = "a";
        int i = 0;
        String tmpWord = "" + ('a' + 1);
        System.out.println(tmpWord);
    }
    
    
    // time out
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0 || beginWord == null || endWord == null)
            return 0;
        wordDict.add(endWord);
        int N = beginWord.length();
        int steps = 0;
        List<String> currLevel = new ArrayList<String>();
        List<String> nextLevel = new ArrayList<String>();
        nextLevel.add(beginWord);
        while (true) {
            steps++;
            wordDict.removeAll(currLevel);
            currLevel.clear();
            currLevel.addAll(nextLevel);
            nextLevel.clear();
            for (int i = 0; i < currLevel.size(); i++) {
                String currWord = currLevel.get(i);
                char[] currCharArray = currWord.toCharArray();
                for (int k = 0; k < N; k++) {
                    char oldChar = currCharArray[k];
                    for (char c = 'a'; c <= 'z'; c++) {
                        currCharArray[k] = c;
//                        String tmpWord = currWord.substring(0, i) + ('a' + j) + currWord.substring(i + 1, N);
                        String newStr = new String(currCharArray);
                        if (wordDict.contains(newStr)) {
                            if (newStr.equals(endWord))
                                return steps + 1;
                            else
                                nextLevel.add(newStr);
                        }
                    }
                    currCharArray[k] = oldChar;
                }
            }
            if (nextLevel.size() == 0)
                return 0;
        }
    }
}
