package org.work.spotoffer;

public class E001_GetMinInRotateArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,2,3,4,5,6,7,8};
		int[] b = {1,0,1,1,1,1};
//		for(int i = 0; i < a.length; i++){
//			int 
//		}
		E001_GetMinInRotateArray obj = new E001_GetMinInRotateArray();
//		System.out.println(obj.getMin(b));
		obj.rotateArray(a, 2);
		for(int n : a)
			System.out.println(n);
	}
	
	//method1, O(N * k), space: O(1) 
	public void rotateArray(int[] a, int k){
		int n = a.length;
		k = k % n;
		while(k > 0){
			int tmp = a[n - 1];
			for(int i = n - 1; i > 0; i--)
				a[i] = a[i - 1];
			a[0] = tmp;
			k--;
		}
	}
	//method 2, O(N), space:O(N)
	public void rotateArray(int[] a, int[] b, int k){
		int n = a.length;
		for(int i = 0; i < n; i++){
			b[(i + k) % n] = a[i];
		}
	}
	//assume a has at least 1 element
	public int getMin(int[] a){
		int l = 0, r = a.length - 1;
		
		while(l < r){
			if(a[l] < a[r])//no rotation
				return a[l];
			
			int m = l + ((r - l) >> 1);
			if(a[l] < a[m]){
				//left is sorted
				l = m + 1;
			}else if(a[l] > a[m]){
				r = m;
			}else
				l++;
		}
		return a[l];
	}
	
	//more optimization
	public int getMin2(int[] a){
		int l = 0, r = a.length - 1;
		
		while(l < r){
			if(a[l] < a[r])//no rotation
				return a[l];
			
			int m = l + ((r - l) >> 1);
			if(a[l] < a[m]){
				//left is sorted
				l = m + 1;
			}else if(a[l] > a[m]){
				r = m;
			}else{
				//3 sub case
				if(a[m] < a[r])
					return a[m];
				else if(a[m] > a[r])
					l = m + 1;
				else //case a[l] = a[m] = a[r]
					l++;
			}
				
		}
		return a[l];
	}
}
