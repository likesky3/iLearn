package org.work.basic.exception;

class VeryImportantException extends Exception{

	private static final long serialVersionUID = 1L;

	public String toString(){
		return "A very important exception!";
	}
}

//class CCError extends Throwable{
//	
//}
public class E01_CheckedException {

	/**
	 * compiler error: unhandled exception type VeryImportantException
	 */
//	void f() {
//		throw new VeryImportantException();
//	}
	
	//fix 1: Add throws declaration
	void f1() throws VeryImportantException {
		throw new VeryImportantException();
	}
	
	//fix 2: Add try/catch block
	void f2() {
		try {
			throw new VeryImportantException();
		} catch (VeryImportantException e) {
			// below is not a good way of handling exception
			e.printStackTrace();
			System.out.println("exception handled");
		}
	}
	
	public static void main(String[] args){
		E01_CheckedException obj = new E01_CheckedException();
		obj.f2();
	}
}
