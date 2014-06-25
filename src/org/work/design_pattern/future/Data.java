package org.work.design_pattern.future;

public class Data {
	private int count;
	private char c;
	public Data(int count, char c) {
		this.count = count;
		this.c = c;
	}
	
	public String getContent() {
		return "cotent is:¡¡" + count + " " + c;
	}
}
