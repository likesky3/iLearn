package org.work.basic.thread;

public class ThreadDemo {

	public static void main(String[] args) {
		RunnableImpl runobj = new RunnableImpl();
		Thread thread1 = new Thread(runobj);
//		Thread thread2 = new Thread(runobj);
		thread1.start();
		runobj.synClass();
//		thread2.start();
		
	}
	
	

}

class RunnableImpl implements Runnable{
	int x, y;
	public void run(){
		synInstance();
	}
	public void foo(){
		for(int i = 0; i < 100; i++){
			synchronized(this){
				x = 1;
				y = 2;
			}
		}
		System.out.println(x + " " + y);
	}
	public synchronized void synInstance(){
		for(int i  = 0; i < 10; i++){
			System.out.println("instance");
			Thread.yield();
		}
	}
	
	public static synchronized void synClass(){
		for(int i  = 0; i < 10; i++){
			System.out.println("static");
			Thread.yield();
		}
	}
}

