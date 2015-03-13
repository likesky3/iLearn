package org.work.basic.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++)
			exec.execute(new LifeOff());
		exec.shutdown();
	}
	
	private Object synObject = new Object();
	
	public void f(){
		//non-critical code
		synchronized (synObject) {
			//this code can be accessed by only one task at a time
		}
		//non-critical code
	}
}

class LifeOff implements Runnable{
	public void run(){
		
	}
}
