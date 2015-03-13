package org.work.others;

import java.util.HashMap;

public class FindSpecialString {

	public static void main(String[] args) {
		String res = findsubstring("abbcddefghhh");
		System.out.println("res: " + res); 
	}

	public static String findsubstring(String a) {
		if (a == null || a.length() < 4)
			return a;

		int len = 0, max = 3;
		int n = a.length();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		char deletedChar = ' ';
		int maxStart = 0, currStart = 0;
		for (int i = 0; i < n; i++) {
			char c = a.charAt(i);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
				len++;
			} else {
				// map already contains 3 distinct chars, remove the currStart char before adding new comer
				if (map.size() >= 3) {
					// update max
					if (len > max){
						max = len;
						maxStart = currStart;
					}
					// remove currStart char
					deletedChar = a.charAt(currStart);
					while (deletedChar == a.charAt(currStart))
						currStart++;
					map.remove(deletedChar);
					// update len
					len = i - currStart + 1;
				}
				
				//add new char
				map.put(c, 1);
//				len++;
			}
		}
		if(len > max)
			maxStart = currStart;
		return a.substring(maxStart, maxStart + max);
	}

}
