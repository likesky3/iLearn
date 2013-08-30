package org.work.basic.exception;

class HoHumException extends Exception {
	public String toString() {
		return "A trivial exception";
	}
}

public class E02_MissException {
	void f() throws VeryImportantException {
		throw new VeryImportantException();
	}

	void dispose() throws HoHumException {
		throw new HoHumException();
	}

	public static void main(String[] args) {
		try {
			E02_MissException missException = new E02_MissException();
			try {
				missException.f();
			} finally {
				missException.dispose();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
