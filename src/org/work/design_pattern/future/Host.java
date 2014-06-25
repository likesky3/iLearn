package org.work.design_pattern.future;

public class Host {
	public Data requst(final int count, final char c) {
		System.out.println("requst_" + count + "_" + c + " BEGIN");
		final FutureData future = new FutureData();
		new Thread() {
			public void run() {
				RealData realData = new RealData(count, c);
				try {
					future.setRealData(realData);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		System.out.println("requst_" + count + "_" + c + " END");
		return future;
	}
}
