package org.work.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class E090_FindSubstring {

	public static void main(String[] args) {
		E090_FindSubstring obj = new E090_FindSubstring();
		ArrayList<Integer> res = obj.findSubstring("barfoohahthefoobarhah",
				new String[] { "foo", "bar","hah" });
		System.out.println(res);
	}

	//version 1, not correct
	public ArrayList<Integer> findSubstring2(String S, String[] L) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (S.isEmpty() || L.length == 0)
			return result;

		int unitLength = L[0].length();
		for (int i = 0; i < L.length; i++) {
			
			
			String prefix = L[i];
			
			int subBeg = 0;
			int subEnd = 0;
			int end = S.length() - prefix.length();
			while (subBeg <= end) {
				subBeg = S.indexOf(prefix, subEnd);
				if(subBeg == -1)
					break;
				
				Set<String> words = new HashSet<String>();
				for (int j = 0; j < L.length; j++)
					words.add(L[j]);
				
				int counter = 1;
				int currBeg = subBeg + unitLength;
				while (counter < L.length) {
					int currEnd = currBeg + unitLength;
					String nextWord = null;
					if(currEnd <= S.length())
						nextWord = S.substring(currBeg, currEnd);
					if (words.remove(nextWord) == false){
						break;
					}
					currBeg = currEnd;
					counter++;
				}

				if (counter == L.length) {
					result.add(subBeg);
				}
				
				subEnd = currBeg;
			}
		}

		return result;
	}
	
	//version 2, 逐个取S中n * m长度的substring, 验证它是否包含L中的每个word
	public ArrayList<Integer> findSubstring(String S, String[] L) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (S.isEmpty() || L.length == 0)
			return result;
		
		//record each word in L and its occurrence 
		HashMap<String, Integer> words = new HashMap<String, Integer>();
		//record occurrences of each word of  L  in subString of S
		HashMap<String, Integer> currStr = new HashMap<String, Integer>();
		
		//populate words
		for(String word : L){
			if(words.containsKey(word))
				words.put(word, words.get(word) + 1);
			else
				words.put(word, 1);
		}
		int n = L.length; //number of words in L
		int m = L[0].length(); //length of each word
		int lastIndex = S.length() - n * m;
		for(int i = 0; i <=lastIndex; i++ ){
			currStr.clear();
			int j;
			for( j = 0; j < n; j++){
				int offset = j * m; //@-@1
				String w = S.substring(i + offset, i + offset + m);
				if(!words.containsKey(w))
					break;
				if(currStr.containsKey(w))
					currStr.put(w, currStr.get(w) + 1);
				else
					currStr.put(w, 1);
				if(currStr.get(w) > words.get(w))
					break;
			}
			if(j == n)
				result.add(i);
		}
		
		return result;
	}
}
