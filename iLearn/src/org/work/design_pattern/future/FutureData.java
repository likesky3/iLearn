package org.work.design_pattern.future;

public class FutureData extends Data{
	public FutureData() {
		super(0,'o');
		System.out.println("future data constructor");
	}

	private RealData realData = null;
	private boolean ready = false;
	public synchronized void setRealData(RealData data) throws InterruptedException {
		if (ready)
			return;
		this.realData = data;
		this.ready = true;
		Thread.sleep(1000);
		notifyAll();
	}
	
	public synchronized String getContent() {
		while(!ready) {
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return realData.getContent();
	}
}
