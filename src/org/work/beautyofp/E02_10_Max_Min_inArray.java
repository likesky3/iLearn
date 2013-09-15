package org.work.beautyofp;

public class E02_10_Max_Min_inArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		E02_10_Max_Min_inArray obj = new E02_10_Max_Min_inArray();
		int[] a = {3, 5, -2, 9,0, 3, -7};
		obj.search(a);
	}
	
	//M1
	public void search(int[] a){
		int max = 0, min = 0;
		if(a[0] < a[1]){
			min = a[0];
			max = a[1];
		}else{
			min = a[1];
			max = a[0];
		}
		
		int i;
		for( i = 2; i < a.length - 1; i += 2){
			if(a[i] < a[i + 1]){
				if(a[i] < min)
					min = a[i];
				if(a[i + 1] > max)
					max = a[i + 1];
			}else{
				if(a[i + 1] < min)
					min = a[i + 1];
				if(a[i] > max)
					max = a[i];
			}
		}
		//do you remember?
		if(i < a.length){
			if(a[i] > max)
				max = a[i];
			if(a[i] < min)
				min = a[i];
		}
		System.out.println(max + " " + min);
	}
	
	//M2: divide & conquer
//	public int[] search2(int[] a, int start, int end){
//		
//	}
	
	

}
