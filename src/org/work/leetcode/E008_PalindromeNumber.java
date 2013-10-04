package org.work.leetcode;

public class E008_PalindromeNumber {
	
	public static void main(String[] args){
		E008_PalindromeNumber obj = new E008_PalindromeNumber();
		System.out.println(obj.isPalindrome3(121));
		
	}
	
	//used extra space
	public boolean isPalindrome(int x) {
        
        if(x < 0)
            return false;
        
        String s = String.valueOf(x);
        int i = 0; 
        int j = s.length() - 1;
        //flag = true;
        
        for(;i < j; i++, j--){
            
            if(s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
	
	//used a little extra space
	public boolean isPalindrome_V2(int x){
		 if(x < 0)
			 return false;
		 
		 int div = 1;
		 while(x / 10 >= div){
			 div *= 10;
		 }
		 
		 while(x > 9)
		 {
			 int high = x / div;
			 int low = x % 10;
			 
			 if(high != low)
				 return false;
			 x = x % div / 10;
			 div /= 100;
		 }
		 
		 return true; 
	}
	
	public boolean isPalindrome3(int x) {
        if(x < 0)
            return false;
        if(x < 10)
            return true;
        
        int hundreds = 1;
        int xcopy = x;
        while(xcopy >= 10){
            xcopy = xcopy / 10;
            hundreds *= 10;
        }
        System.out.println(hundreds);
        int front = 0, end = 0;
        while(x >= 10){
            front = x / hundreds;
            end = x % 10;
            
            if(front != end)
                return false;
            x = (x % hundreds) / 10;
            hundreds /= 100;
        }
        return true;
    }
}
