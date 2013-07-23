package org.work.leetcode;

//no DP method can not solve the problem
public class E048_InterleavingString_failed {
	
	public static void main(String[] args){
		E048_InterleavingString_failed obj = new E048_InterleavingString_failed();
		System.out.println(obj.isInterleave("aa", "aba", "aabaa"));
	}
	
	//idea1:
	//for a char at s3, first compare with curr char at s1, then s2.
	//if unmatched in the process, break and do comparison in an opposite order,
	//that is, compare with s2 first, then s1
	//this strategy will fail in face of case: s1= aabcc, s2=dbbca, s3=aadbcbbcac
	//interleave across s1 and s2 randomly
	
	//idea2:
	//to cope with the problem described above, when come a char of s3, 
	//compare it with both s1 & s2, if both hit, then add one char more and compare,
	//if still both hit, then add one more till at least one miss
	//failed @ s1: aa, s2:ab, s3: aaba
	public boolean isInterleave(String s1, String s2, String s3) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(s1 == null && s2 == null)
        	return s3 == null;
        else if(s1.isEmpty() && s2.isEmpty())
        	return s3.isEmpty();
        else if(s1 == null ||s1.isEmpty())
        	return s2.equals(s3);
        else if(s2 == null || s2.isEmpty())
        	return s1.equals(s3);
        
		if(s1.length() + s2.length() < s3.length())
			return false;
		
        if(check(s1, s2, s3))
        	return true;
        StringBuffer sb1 = new StringBuffer(s1);
        StringBuffer sb2 = new StringBuffer(s2);
        StringBuffer sb3 = new StringBuffer(s3);
        s1 = sb1.reverse().toString();
        s2 = sb2.reverse().toString();
        s3 = sb3.reverse().toString();
        return check(s1, s2, s3);
    }
	
	private boolean check(String s1, String s2, String s3){
		boolean isInterleaving = false;
        //3 strings are not null
        int i1 = 0, i2 = 0, i3 = 0, i1w = 0, i2w = 0;
        int endIndex1 = s1.length() - 1;
        int endIndex2 = s2.length() - 1;
        StringBuffer currStr = new StringBuffer();
//        boolean oneMore = false;
        while(i3 < s3.length()){
        	currStr.append(s3.charAt(i3));
            if(i1w <= endIndex1 && s1.substring(i1, i1w + 1).equals(currStr.toString())){
            	
            	if(i2w <= endIndex2 && s2.substring(i2, i2w + 1).equals(currStr.toString())){
            		//both s1 & s2 hit
            		
            		if(i1w == endIndex1 && i2w == endIndex2){
            			isInterleaving = s3.substring(i3 + 1, s3.length()).equals(currStr.toString());
            			break;
            		}else{
            		//currStr can append one more character
            		if(i1w < endIndex1) i1w++;
            		if(i2w < endIndex2) i2w++;
            		i3++;
            		}
            	}else{
                	//s1 hits, s2 misses
                	if(i1w == endIndex1){
                		isInterleaving = s3.substring(i3 + 1, s3.length()).equals(s2.substring(i2, endIndex2 + 1));
                		break;
                	}
                	i2w = i2; //s2 pull back
                	i1 = ++i1w;
                	i3++;
                	currStr = new StringBuffer();
                }
            }else{
            	if(s2.substring(i2, i2w + 1).equals(currStr.toString())){
            		//s1 misses, s2 hits
            		if(i2w == endIndex2){
            			isInterleaving = s3.substring(i3 + 1, s3.length()).equals(s1.substring(i1, endIndex1 + 1));
            			break;
            		}
            		i1w = i1;
            		i2 = ++i2w;
            		i3++;
            		currStr = new StringBuffer();
            	}else if(currStr.length() == 1){
            		isInterleaving = false;
            		break;
            	}
            	else{
            		//both s1 & s2 miss, but it's assured that both s1 & s2 hits the currStr but last one char
            		//currStr[0, currStr.length() - 2] can retrieved from either s1 or s2, here we suppose from s1
            		i1 = i1w;
            		i2w = i2;
            		currStr = new StringBuffer();
            	}
            	
            }
        }
        return isInterleaving;
	}
}

/*
1] at least easy errors, such as mistaken s1 with s2,
omitted s1.isEmpty(), forgot correct methods of String,
not consider the guard conditions: i1 < s1.length()
2] fail test case: aa, ab, aaba
*/