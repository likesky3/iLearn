package org.work.beautyofp;

import java.util.ArrayList;

public class E32_PhoneNum2Word {

	public static void main(String[] args) {
		E32_PhoneNum2Word obj = new E32_PhoneNum2Word();
		String phoneNum = "13732213760";
		obj.dfs(0, phoneNum.length(), phoneNum, new StringBuilder());
		for (String word : obj.wordsBag) {
			System.out.println(word);
		}
	}

	public E32_PhoneNum2Word() {
		wordsBag = new ArrayList<String>();
		map[0] = "".toCharArray();
		map[1] = "".toCharArray();
		map[2] = "abc".toCharArray();
		map[3] = "def".toCharArray();
		map[4] = "ghi".toCharArray();
		map[5] = "jkl".toCharArray();
		map[6] = "mno".toCharArray();
		map[7] = "pqrs".toCharArray();
		map[8] = "tuv".toCharArray();
		map[9] = "wxyz".toCharArray();
	}

	public ArrayList<String> wordsBag = null;
	public char[][] map = new char[10][0];

	private void dfs(int dep, int maxDep, String number, StringBuilder word) {
		if (dep == maxDep) {
//			System.out.println(word.toString());
			wordsBag.add(word.toString());
			return;
//			word = new StringBuilder();
		}
		char[] chars = map[number.charAt(dep) - '0'];
		if(chars.length == 0) //case '0' & '1'
			dfs(dep + 1, maxDep, number, word);
		for (char c : chars) {
			word.append(c);
			dfs(dep + 1, maxDep, number, word);
			word.deleteCharAt(word.length() - 1);
		}

	}

}
