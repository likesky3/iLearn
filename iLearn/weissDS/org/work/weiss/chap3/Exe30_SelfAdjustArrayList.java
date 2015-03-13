package org.work.weiss.chap3;

public class Exe30_SelfAdjustArrayList<T> extends Exe09_MyArrayList<T>{

	public static void main(String[] args) {
		Exe30_SelfAdjustArrayList<Integer> instance = new Exe30_SelfAdjustArrayList<>();
		System.out.println(instance.find(0));
		for (int i = 0; i < 5; i++)
			instance.add(i);
		System.out.println(instance.find(8));
		System.out.println(instance.find(1));
		instance.print();
	}
	
	public void print() {
		for (int i = size - 1; i >= 0; i--)
			System.out.print(theItems[i] + "\t");
	}
	//O(N),LRU
	//TODO: binary search
	public boolean find(T value) {
		for (int i = 0; i < size; i++) {
			if (value.equals(theItems[i])) {
				for (int j = i; j < (size - 1); j++)
					theItems[j] = theItems[j + 1];
				theItems[size - 1] = value;
				return true;
			}
		}
		return false;
	}
}
