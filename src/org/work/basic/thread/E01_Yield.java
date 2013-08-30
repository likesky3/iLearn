package org.work.basic.thread;

public class E01_Yield {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new FirstRunnable()).start();
		}
		System.out.println("Thread demo start");
	}
}

class FirstRunnable implements Runnable {
	private static int taskCount = 0;
	private final int id = taskCount++;

	public FirstRunnable() {
		System.out.println("#"+ id + " " +"task start!");
	}

	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("#"+ id + " " + i);
			Thread.yield();
		}

		System.out.println("#"+ id + " " +"task end.");
	}
}
