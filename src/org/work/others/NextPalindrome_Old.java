package org.work.others;

import java.util.Scanner;

public class NextPalindrome_Old {

	public static void main(String[] args) {
		//step1 : read N A from console
		//step2: get a palindrome P from the first N/2 digits
		//step3: compare A & P
		//step4: if(A < P) return P, else increase the first N/2 digits by 1 
		NextPalindrome_Old generator = new NextPalindrome_Old();
		String N = args[0];
		try{
			generator.getNextPalindrome(N);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public String getNextPalindrome(String N) throws Exception{
		//step 0, get abstract value of number N
		char firstChar = N.charAt(0);
		boolean isPositive = firstChar == '+' ? true : false;
		String absN = N;
		if(!isPositive)
			absN = N.substring(1);
		
		//step 1, standardize input N
		absN = standardizeInput(absN);
		
		//step 2, generate a candidate palindrome P for abs(N)
		StringBuilder P = generateCandidate(N);
		
		//step 3, compare absN with P
		int compareResult = compare2Num(N, P.toString());
		
		//step 4, if P < N, generate next larger palindrome
		if(isPositive && compareResult == 1 || !isPositive && compareResult == -1){
			
		}
		
		return null;
	}
	
	private String standardizeInput(String N) throws Exception{
		int start = 0;
		boolean isZero = true;
		for(int i = 0; i < N.length(); i++){
			if(!Character.isDigit(N.charAt(i)))
				throw new RuntimeException("Illegal input, please input a N");
			else if(isZero){
				if(N.charAt(i) == '0')
					start++;
				else
					isZero = false;
			}
		}
		if(start == N.length())
			return "0";
		else
			return N.substring(start);
	}
	
	//generate a palindrome P according to the front half digits of N
	private StringBuilder generateCandidate(String N){
		StringBuilder candidate = new StringBuilder();
		int len = N.length();
		StringBuilder frontHalf = new StringBuilder(N.substring(0, len >> 1));
		candidate.append(frontHalf);
		if((len & 1) == 1) //number of digits in N is odd, append the middle digit
			candidate.append(N.charAt((len >> 1) + 1));
		candidate.append(frontHalf.reverse());
		return candidate;
	}
	//compare 2 non-negative number
	private int compare2Num(String a, String b){
		if(a == null && b == null || a.length() == 0 && b.length() == 0)
			return 0;
		if(a == null || a.length() == 0)
			return -1;
		if(b == null || b.length() == 0)
			return -1;
		
		int len1 = a.length();
		int len2 = b.length();
		int res = 0;
		
		if(len1 == len2){//same length
			res = a.compareTo(b);
		}else{//different length, longer N is larger
			res = len1 > len2 ? 1 : -1;
		}
		return res;
	}
	
//	private String generateLargerPalin(StringBuilder P, boolean isPositive){
//		
//	}
}
