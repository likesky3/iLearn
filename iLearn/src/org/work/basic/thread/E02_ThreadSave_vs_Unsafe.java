package org.work.basic.thread;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E02_ThreadSave_vs_Unsafe {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		TestArray array = new TestArray();
		for (int i = 0; i < 0; i++) {
			exec.execute(new Task(array, i));
		}
		exec.shutdownNow();
		
//		System.out.println("--------------------");
		
		ExecutorService exec2 = Executors.newCachedThreadPool();
		TestVector vector  = new TestVector();
		for (int i = 0; i < 200; i++) {
			exec2.execute(new Task2(vector, i));
		}
		exec2.shutdownNow();
	}
}

class TestArray {
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();

	public TestArray() {
		arrayList.add(100);
	}

	public void set(int i) {
		arrayList.set(0, i);
	}
	
	public void decrease(){
		arrayList.set(0, arrayList.get(0) - 1);
	}

	public int get() {
		return arrayList.get(0);
	}
}

class TestVector {
	private Vector<Integer> vector = new Vector<Integer>();

	public TestVector() {
		vector.add(100);
	}

	public void set(int i) {
		vector.set(0, i);
	}
	
	public void dec(){
		vector.set(0, vector.get(0) - 1);
	}

	public int get() {
		return vector.get(0);
	}
}

class Task implements Runnable {
	TestArray in_a;
	int newValue;
	public Task(TestArray a, int i){
		in_a = a;
		newValue = i;
	}
	public void run() {
//		System.out.println("old: " + in_a.get());
//		in_a.set(newValue)
		in_a.decrease();
		Thread.yield();
//		System.out.println("new: " + in_a.get());
//		System.out.println("===========");
		}
	}


class Task2 implements Runnable {
	TestVector in_a;
	int newValue;
	public Task2(TestVector a, int i){
		in_a = a;
		newValue = i;
	}
	public void run() {
//		System.out.println("old: " + in_a.get());
//		in_a.set(newValue);
		in_a.dec();
		Thread.yield();
		System.out.println("new: " + in_a.get());
		}
}
