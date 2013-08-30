package org.work.leetcode;

import java.util.ArrayList;

public class E045_RemoveDupFromSortedArrayII {
	public static void main(String[] agrs){
		E045_RemoveDupFromSortedArrayII obj = new E045_RemoveDupFromSortedArrayII();
		int[] A = {1,1,1,1,2,2,3,3,3,4,5};
		int len = obj.removeDuplicates(A);
		for(int i = 0; i < A.length; i++){
			System.out.println(A[i]);
		}
		System.out.println("----------------");
		System.out.println(len);
	}
	
	//Time: O(N), Space: O(N)
	public int removeDuplicates(int[] A) {
        
        if(A.length < 2)
            return A.length;
            
        boolean isdup = false;
        int prev = A[0];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int num :A){
        	list.add(num);
        }
        
        //int ii = 1;
        int dupnum = 0;
        for(int i = 1; i < A.length; i++){
            if(A[i] != prev)
                isdup = false;
            else if(isdup){
                list.remove(i - dupnum);
                dupnum++;
            }
            else
                isdup = true;
            prev = A[i];
        }
        
        
        for(int i = 0; i < list.size(); i++){
        	A[i] = list.get(i);
        }
        return list.size(); 
    }
	
	//others
	//Time: O(N), Space: O(1)
	public int removeDuplicatesV2(int[] A){
		if(A.length < 2)
			return A.length;
		
		int dupnum = 1;
		int actual = 1;
		for(int i = 1; i < A.length; i++){
			if(A[i] != A[i-1]){
				dupnum = 1;
				A[actual++] = A[i];
			}
			else if(dupnum == 1){
				dupnum++;
				A[actual++] = A[i];
			}
		}
		
		return actual;
	}
	
	//mine, 2nd,Time: O(N), Space: O(1)
	//"remains" 指针指向结果数组后面一个元素，同时也代表保留的数组元素个数
	//从第三个元素开始扫描，遇到一个新元素，赋给remains指针位置处，remains后移一位，
	//同时代表当前元素出现个数的counter 设为1
	//遇到重复元素时，counter加1
	//counter小于3，这个元素也需要保留，所以赋给remains，然后remains后移
	public int removeDuplicatesV3(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(A.length <= 2)
            return A.length;
        
        int remains = 2;
        int counter = A[0] == A[1] ? 2 : 1;
        
        for(int i = 2; i < A.length; i++){
            if(A[i] != A[i - 1]){
                A[remains++] = A[i];
                counter = 1; // a brand new number
            }else{
                counter++;
                if(counter < 3)
                    A[remains++] = A[i];
            }
            
        }
        return remains;
    }
	
}
