package org.work.basic.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords obj = new SubstringWithConcatenationOfAllWords();
        String[] words = {"fooo","barr","wing","ding","wing"};
        for (int idx : obj.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", words)) {
            System.out.println(idx);
        }
    }
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();
        if (s == null || s.length() == 0 || words == null || words.length == 0)
            return result;
        int m = words.length, n = s.length();
        int singleLen = words[0].length();
        int targetLen = m * singleLen;
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        for (int i = 0; i < m; i++) {
            if (!dict.containsKey(words[i]))
                dict.put(words[i], i);
            else
                dict.put(words[i], dict.get(words[i]) + 1);
        }
        int[] table = new int[m];
        int end = n - targetLen;
        for (int i = 0; i <= end; i++) {
            for (int k = 0; k < m; k++) {
                table[k] = 0;
            }
            int j = i;
            StringBuilder sb = new StringBuilder();
            while (j < i + targetLen) {
                String curr = s.substring(j, j + singleLen);
                sb.append(curr).append(" ");
                Integer idx = dict.get(curr);
                if (idx != null && table[idx] == 0) {
                    table[idx] = 1;
                    j += singleLen;
                } else {
                    break;
                }
            }
            System.out.println(i + " " + sb.toString());
            if (j == i + targetLen)
                result.add(i);
        }
        return result;
    }
}
