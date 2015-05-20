package org.work.twoPointers;

public class ValidPalindrome {
    // method 1
    public boolean isPalindrome1(String s) {
        if (s == null)
            return false;
        if (s.trim().equals(""))
            return true;
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (isAlphanumeric(s.charAt(i)))
                sb.append(s.charAt(i));
        }
        if (sb.length() == 0)
            return true;
        int i = 0, j = sb.length() - 1;
        while (i < j) {
            if (!equalsIgnoreCase(sb.charAt(i), sb.charAt(j)))
                return false;
            i++;
            j--;
        }
        return true;
    }
    
    private boolean isAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }
    private boolean equalsIgnoreCase(char a, char b) {
        return a == b || (Math.abs(a - b) == 'a' - 'A');
    }
    // method 2
    public boolean isPalindrome2(String s) {
        if(s == null || s.length() == 1)
            return true;
        
        for(int i = 0, j = s.length() - 1; i < j;){
            if(!isAlphanumeric(s.charAt(i)))
                i++;
            else if(!isAlphanumeric(s.charAt(j)))
                j--;
            else{ 
                int front = s.charAt(i++);
                if(front >= 'A' && front <= 'Z')
                    front += 'a' - 'A';
                int end = s.charAt(j--);
                if(end >= 'A' && end <= 'Z')
                    end += 'a' - 'A';
                if(front != end)
                    return false;
            }
        }
        return true;
    }
    
    // method 3
    public boolean isPalindrome3(String s) {
            s=s.toLowerCase();
            s=s.replaceAll("[^a-z0-9]","");

            for(int i=0;i<s.length()/2;i++)
            {
                if(s.charAt(i)!=s.charAt(s.length()-i-1))
                return false;
            }

            return true;
    }
}
