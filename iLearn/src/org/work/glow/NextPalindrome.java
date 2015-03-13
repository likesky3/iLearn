package org.work.glow;

import java.math.BigInteger;

public class NextPalindrome {

	public static void main(String[] args) {
		NextPalindrome generator = new NextPalindrome();
		String N = args[0];
//		String N = "-101";
		try {
			BigInteger result = generator.getNextPalindrome(N);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public BigInteger getNextPalindrome(String N) throws Exception {
		if(N == null || N.length() == 0)
			throw new RuntimeException("Illegal input: null or empty");
		
		// step 0, get abstract value of number N
		boolean isPositive =  N.charAt(0) == '-' ? false : true;
		BigInteger absN = new BigInteger(N);
		if (!isPositive)
			absN = absN.negate();

		// step 1, generate a candidate palindrome P for abs(N)
		BigInteger P = generateCandidate(absN.toString());

		// step 2, compare absN with P
		int compareResult = absN.compareTo(P);
		
		// step 3, if P < N, generate next larger palindrome
		if (isPositive && compareResult != -1 
				|| !isPositive && compareResult != 1) {
			P =  getLargerPalindrome(P, isPositive);
		}

		return isPositive ? P : P.negate();
	}

	// generate a palindrome P according to the front half digits of N
	public BigInteger generateCandidate(String strN) {
		StringBuilder candidate = new StringBuilder(strN.length());
		
		int len = strN.length();
		StringBuilder frontHalf = new StringBuilder(strN.substring(0, len >> 1));
		candidate.append(frontHalf);//append front half
		if ((len & 1) == 1) // number of digits in N is odd, append the middle digit
			candidate.append(strN.charAt(len >> 1));
		candidate.append(frontHalf.reverse());//append reverse of the front half
		return new BigInteger(candidate.toString());
	}
	
	//generate a larger palindrome than the parameter P
	//idea: front half + 1, reverse
	//e.g. 1221, front half = 12 -> 13, result is 1331
	public BigInteger getLargerPalindrome(BigInteger P, boolean isPositive) throws Exception{
		if(P.compareTo(new BigInteger("0")) == -1)
			throw new RuntimeException("Illegal input, P should be non-negative value.");
		
		BigInteger delta = isPositive ? new BigInteger("1") : new BigInteger("-1");
		
		//special case 1, positive number, all digits are 9 
		if(isPositive && all9s(P)){
			P = P.add(new BigInteger("2") );
			return P;
		}
		//special case 2, negative number, starts and ends with digit 1, other digits are 0
		if(!isPositive && start1End1Mid0(P)){
			P = P.add(new BigInteger("-2"));
			return P;
		}
		
		//[-9, -1] && [0,8]
		if(P.compareTo(new BigInteger("10")) == -1){
			P = P.add(delta);
			return P;
		}
		
		String strP = P.toString();
		int halfLen = strP.length() + 1 >> 1; //first half of 1221 is 12, first half of 12321 is 123
		StringBuilder largerPalin = new StringBuilder(strP.length());
		
		BigInteger firstHalfVal = new BigInteger(strP.substring(0, halfLen));
		 firstHalfVal = firstHalfVal.add(delta); //increase first half with delta 
		 largerPalin.append(firstHalfVal.toString());//append the first half
		 
		 //second half is the reverse of the first half
		StringBuilder secondHalf = new StringBuilder(largerPalin);
		secondHalf.reverse();
		
		boolean isLenEven = (strP.length() & 1) == 0;
		if(isLenEven)
			largerPalin.append(secondHalf);
		else
			largerPalin.append(secondHalf.substring(1)); //the 0th char of second half is the middle char of the target palindrome
		
		return new BigInteger(largerPalin.toString());
	}
	
	//check if all digits in P is 9
	public boolean all9s(BigInteger P){
		String strP = P.toString();
		for(int i = 0; i < strP.length(); i++)
			if(strP.charAt(i) != '9')
				return false;
		return true;
	}
	
	//check if P starts and ends with digit 1, other digits are 0
	public boolean start1End1Mid0(BigInteger P){
		String strP = P.toString();
		int len = strP.length();
		if(strP.charAt(0) != 1 || strP.charAt(len - 1) != 1)
			return false;
		for(int i = 1; i < len - 1; i++)
			if(strP.charAt(i) != '0')
				return false;
		return true;
	}

}
