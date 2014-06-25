package org.work.design_pattern.future;

public class FuturePattern {

	public static void main(String[] args) {
		System.out.println("main BEGIN");
		Host host = new Host();
		Data data1 = host.requst(10, 'A');
		Data data2 = host.requst(20, 'B');
		
		System.out.println("main other job BEGIN");
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			
		}
		System.out.println("main other job END");
		System.out.println("data1=" + data1.getContent());
		System.out.println("data2=" + data2.getContent());
	}

}
