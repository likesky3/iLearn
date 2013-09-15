/**
 * ValidPalindrome.java 
 *
 * Copyright (c) 2011 State Street Bank and Trust Corp.
 * 225 Franklin Street, Boston, MA 02110, U.S.A.
 * All rights reserved.
 *
 * ValidPalindrome.java is the copyrighted,
 * proprietary property of State Street Bank and Trust Company and its
 * subsidiaries and affiliates which retain all right, title and interest therein."
 * 
 * Revision History
 * 
 * Date            Programmer                Notes
 * ------------    --------------------      ----------------------------
 * May 17, 2013 	   a524690		     Init
 *
 */
package org.work.leetcode;

/**
 * @author a524690
 * 注意大小写，注意i,j更新的边界，即i < j
 */
public class E012_ValidPalindrome
{
    public boolean isPalindrome(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(s == null || s.length() == 1)
	    return true;
	
        int length = s.length();
        
        for(int i = 0, j = length - 1; i < j; i++, j--){
            while(i < j && !isCharOrNum(s.charAt(i)))
        	i++;
            
            while(j > i && !isCharOrNum(s.charAt(j)))
        	j--;
            if(i == j)
        	return true;
            
    	if(s.charAt(i) != s.charAt(j) && s.charAt(i) != (s.charAt(j) + 'a' - 'A') && s.charAt(i) != (s.charAt(j) - ('a' - 'A')))
    	    return false;
    	
        }
        return true;
    }
    
    boolean isCharOrNum(char c){
	if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
    	    || (c >= '0' && c <= '9'))
	    return true;
	return false;
    }
    
    //Version 2
    public boolean isPalindrome_V2(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(s == null)
        return false;
        if(s.isEmpty())
        return true;
        
        int i = 0; 
        int j = s.length() - 1;
        //flag = true;
        
        for(;i < j; i++, j--){
            while(i < j && !Character.isLetterOrDigit(s.charAt(i)))
                i++;
            while(j > i && !Character.isLetterOrDigit(s.charAt(j)))
                j--;
                
            if(i == j)
                return true;
                
            if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
        }
        return true;
    }
    
}
