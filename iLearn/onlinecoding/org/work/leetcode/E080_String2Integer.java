package org.work.leetcode;


public class E080_String2Integer {

	public static void main(String[] args) {
		E080_String2Integer obj = new E080_String2Integer();
		System.out.println(obj.atoi("    10522545459"));
//		System.out.println(1234567890 * 10);
	}

	public int atoi(String str) {
		if(str == null || str.isEmpty())
            return 0;
		
		//trim white space
		String newStr = str.trim();

		int tmp = 0;
		boolean isPositive = true;
		int len = 0;
		char firstChar = newStr.charAt(0);
		if(firstChar == '-')
			isPositive = false;
		else if(firstChar == '+')
			isPositive = true;
		else if(Character.isDigit(firstChar)){
			tmp = firstChar - '0';
			len++;
		}
		else
			return 0;
		
		for(int j = 1; j< newStr.length(); j++){
			
			char c = newStr.charAt(j);
			if(Character.isDigit(c)){
				len++;
				if(len > 10)
					return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
				tmp = tmp * 10 + newStr.charAt(j) - '0';
			}
			else{// c is not a digit
				break;
			}
			
		}
		
		//check if tmp is in the range of integer
		if(isPositive &&  tmp< 0)
			return Integer.MAX_VALUE; //return once encounter number > 2147483647
		else if(!isPositive && tmp < 0){
			return Integer.MIN_VALUE;
		}
		return isPositive ? tmp : -tmp;
	}

}
