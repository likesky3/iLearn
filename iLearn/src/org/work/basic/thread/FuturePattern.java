package org.work.basic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FuturePattern {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> buy = new FutureTask(new RealData());
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.submit(buy);
		
		Thread.sleep(2000);
		System.out.println("hang out");
		
		System.out.println(buy.get());
		executor.shutdown();
	}

}

class RealData implements Callable<String> {
//	private String para;
	public String call() throws Exception{
//		StringBuffer sb = new StringBuffer();
		Thread.sleep(1000);
		System.out.println("shopping done.");
		return "hah";
	}
}
