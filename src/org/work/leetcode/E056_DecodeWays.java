package org.work.leetcode;

public class E056_DecodeWays {

	/**
	 * discover pattern, use roll array, should be very thoughtful to get Accept
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		E056_DecodeWays obj = new E056_DecodeWays();
		int num = obj.numDecodings("20");
		System.out.println("==============");
		System.out.println(num);
	}
	
	public int numDecodings(String s) {
		if(s.isEmpty() || s.startsWith("0") || s.contains("00"))
			return 0;
		
		if(s.length() == 1)
			return 1;
		
//		if(s.contains("0")) //consider "1010" 
//			return 0;
		
		//check till the end unless find 0 prefixed with not-1 or not-2
		int from = 0;
		while(from >= 0 && from < s.length()){
			from = s.indexOf('0', from);
			if(from == -1)
				break;
			if(s.charAt(from - 1) != '1' && s.charAt(from - 1) != '2')
				return 0;
			from++;
		}
		
		int[] f = new int[3];
		f[0] = 1;
		f[1] = 1;
		int i = 1;
		for(; i < s.length(); i++){
			String sub = s.substring(i - 1, i + 1);
			if(sub.startsWith("0")){
				f[(i + 1) % 3] = f[i % 3];
				continue;
			}
			
			int tmp = Integer.parseInt(sub);
			if(tmp > 26)
				f[(i + 1) % 3] = f[i % 3];
			else if(tmp == 10 || tmp == 20)
				f[(i + 1) % 3] = f[(i - 1) % 3];
			else {
				f[(i + 1) % 3] = f[i % 3] + f[(i - 1) % 3];
			}
		}
		return f[i % 3];
	}
}

//1, failed at "1010", "101"...
//2, failed at "110", "1090"