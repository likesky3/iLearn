package org.work.leetcode;

public class E069_CountAndSay {
	
	public String countAndSay(int n) {
		if( n < 1)
			return "bad input.";
//		String seqNum = 1;
		String seqStr = "1";
		
		for(int i = 2; i <= n; i++){
//			seqNum = Integer.parseInt(seqStr); //seqStr may exceed Integer.Max
			seqStr = read(seqStr);
		}
		return seqStr;
	}
	
	private String read(String input){
		int c = 1;
		StringBuffer res = new StringBuffer();
		for(int i = 1; i < input.length(); i++){
			if(input.charAt(i) == input.charAt(i - 1))
				c++;
			else {
				res.append(c);
				res.append(input.charAt(i - 1));
				c = 1;
			}
		}
		res.append(c);
		res.append(input.charAt(input.length() - 1));
		return res.toString();
	}
}
