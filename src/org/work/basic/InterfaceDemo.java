package org.work.basic;

interface A1 { 
void m1(); 
//protected void m2(); //illegal modifier, only public & abstract are permitted, and they are  by default

//protected int a = 1; //only public & static & final are permitted and they are by default

//static void m3(); //can not have static method
}

interface A2{
	void m2();
}

//interface can extend multiple interface
interface A3 extends A1, A2{
	
}

//can implement multiple interfaces
class C1 implements A1, A2 {  
	public   void m1() {} 
//	void m2(){} //can not reduce the visibility
	public void m2(){}
} 

