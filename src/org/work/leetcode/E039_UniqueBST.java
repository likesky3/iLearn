package org.work.leetcode;

public class E039_UniqueBST {
	public int numTrees(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int[] num = new  int[n + 1];
        num[0] = 1;
        
        for( int i = 1; i <= n; i++){
            num[i] = 0;
            for(int j = 1; j <= i; j++){
            	
                num[i] += (num[j - 1] * num[i -j]); //@error, num[n - j]
                System.out.println(num[i]);
            }
        }
            
        return num[n];
    }
	
	public static void main(String[] args){
		E039_UniqueBST obj = new E039_UniqueBST();
		obj.numTrees(3);
	}
}
