package org.work.leetcode;

public class E008_PalindromeNumber {
	
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
	
}
