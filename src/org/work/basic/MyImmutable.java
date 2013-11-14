package org.work.basic;

//Wrong way to write a constructor:
final class MyImmutableWrong {
	private final int[] myArray;

	public MyImmutableWrong(int[] anArray) {
		this.myArray = anArray; // wrong
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Numbers are: ");
		for (int i = 0; i < myArray.length; i++) {
			sb.append(myArray[i] + " ");
		}
		return sb.toString();
	}
}

final class MyImmutableRight {
	private final int[] myArray;

	// Right way to write an immutable class
	// Right way is to copy the array before assigning in the constructor.
	public MyImmutableRight(int[] anArray) {
		this.myArray = anArray.clone(); // defensive copy
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Numbers are: ");
		for (int i = 0; i < myArray.length; i++) {
			sb.append(myArray[i] + " ");
		}
		return sb.toString();
	}
}

public class MyImmutable {
	public static void main(String[] args) {
		// the caller cannot change the array after calling the constructor.
		int[] array = { 1, 2 };

		MyImmutableWrong myImmutableRef = new MyImmutableWrong(array);
		System.out.println("Before constructing " + myImmutableRef);
		array[1] = 5; // change (i.e. mutate) the element
		System.out.println("After constructing " + myImmutableRef);

		int[] array2 = { 1, 2 };
		MyImmutableRight myImmutableRef2 = new MyImmutableRight(array2);
		System.out.println("Before constructing " + myImmutableRef2);
		array2[1] = 5; // change (i.e. mutate) the element
		System.out.println("After constructing " + myImmutableRef2);
	}
	
}

