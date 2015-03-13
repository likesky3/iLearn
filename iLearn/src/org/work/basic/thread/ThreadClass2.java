package org.work.basic.thread;

public class ThreadClass2 extends Thread {
	private String testThread;

	public static void main(String argv[]) {
		ThreadClass2 th = new ThreadClass2();
		th.methodOne();
	}

	ThreadClass2() {
	}

	ThreadClass2(String str) {
		testThread = str;
	}

	public String getThreadName() {
		return testThread;
	}

	public void methodOne() {
		ThreadClass2 first = new ThreadClass2("first");
		first.start();
		ThreadClass2 second = new ThreadClass2("second");
		second.start();
	}

	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println(getThreadName() + i);
			System.out.println(currentThread());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
