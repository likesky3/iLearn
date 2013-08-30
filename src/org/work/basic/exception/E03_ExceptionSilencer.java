package org.work.basic.exception;

public class E03_ExceptionSilencer {
	public static void main(String[] args){
		try {
			throw new RuntimeException();
		} finally {
			//Using 'return' inside finally block
			//will silence any thrown exception.
			return;
		}
	}
}
