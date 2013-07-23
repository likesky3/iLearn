package org.work.basic;

public class Derived extends Base {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Derived obj = new Derived();
		obj.play();
	}
	
	public void play(){
		System.out.println(i);
	}
	
	private int i = 3;
}
