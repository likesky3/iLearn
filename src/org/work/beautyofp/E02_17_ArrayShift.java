package org.work.beautyofp;

public class E02_17_ArrayShift {

	public static void main(String[] args) {
		E02_17_ArrayShift obj = new E02_17_ArrayShift();
		int[] a = {'a','b', 'c', 1, 2, 3,4};
		obj.printArray(a);
		obj.arrayShiftK(a, 4);
		obj.printArray(a);
		obj.arrayShiftK(a, -4);
		obj.printArray(a);
	}
	
	public void printArray(int[] a){
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	public void arrayShiftK(int[] a, int k){
		int n = a.length;
		if(k > 0){
			//shift right
			k = k % n;
			reverseArray(a, 0, n - k - 1);
			reverseArray(a, n -k, n - 1);
			reverseArray(a, 0,  n - 1);
		}else if(k < 0){
			//shift left
			k = (-k) % n;
			reverseArray(a, 0, k - 1);
			reverseArray(a, k,  n - 1);
			reverseArray(a, 0, n - 1);
		}
		
	}
	
	private void reverseArray(int[] a, int b, int e){
		int tmp;
		for(; b < e; b++, e--){
			tmp = a[e];
			a[e] = a[b];
			a[b] = tmp;
		}
	}

}
