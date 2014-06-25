package org.work.basic;

//can not have final abstract class, choose either abstract or final 
public abstract class AbstractDemo {
	//can have constructor, can have different access control
	AbstractDemo(){System.out.println("empty construct");}
	AbstractDemo(int a){System.out.println("cons 1");}
	protected AbstractDemo(int a, int b){System.out.println("cons 2");}
	public AbstractDemo(int a, int b, int c){System.out.println("cons 3");}
	
	//can have normal field
	private  int a = 1;
	
	//can have implemented method
	private void f(){System.out.println("private implenmented method");}
	final void f2(){m2();} //non static method can call static method
	
	//can have static method
	static void m2(){
		System.out.println("static method");
	}
	
	//method without abstract keyword must have a method body
//	static void m3();
//void m4();
	
	abstract void abstractM4();
	
	public static void main(String[] args) {
		AbstractDemo.m2();
	}

}

class SubClass extends AbstractDemo{
	SubClass(){
		super();
	}
	SubClass(int a){
		super(a);
	}
	void abstractM4() {
		// TODO Auto-generated method stub
		
	}
	
}
