package org.work.leetcode;

//process from end to front, always put the current largest in the last empty field.
//so,the latter part of Array A is always sorted 
public class E044_MergeSortedArrays {
	public void merge(int A[], int m, int B[], int n) {
        int size = m + n;
        if(n <= 0)
            return;
        
        int a = m - 1, b = n - 1;
        for(int i = size - 1; i >= 0; i--){
            //put larger one in position i
            if(a < 0){ //directly copy B into A if A has no elements
                A[i] = B[b];
                b--;
            }else if(b >= 0){//if both A & B has elements, compare and copy the larger one in the last empty field
                if(A[a] >= B[b]){
                    A[i] = A[a];
                    a--;
                }else{
                    A[i] = B[b];
                    b--;
                }
            }else{
                break; //ends when all elements in B are copied into A 
            }
        }
        
    }
	
	//take advantage of E048_Interleaving String
	public void merge2(int A[], int m, int B[], int n) {
        if(n == 0)
            return;
            
        int i = m - 1;
        int j = n - 1;
        while(i >= 0 && j >= 0){
            A[i + j + 1] = A[i] >= B[j] ? A[i--] : B[j--];//special note to left side index
        }
        
        while(j >= 0){
//            A[j--] = B[j]; //equals to A[j] = B[j - 1];
        	 A[j] = B[j];
        	 j--;
        }
    }
	
	public static void main(String[] args){
		E044_MergeSortedArrays obj = new E044_MergeSortedArrays();
		int[] A = new int[10];
		int[] B = new int[]{1,2,3,4};
		obj.merge2(A, 0, B, 4);
	}
}
