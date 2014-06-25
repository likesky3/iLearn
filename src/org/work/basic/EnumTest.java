package org.work.basic;

public class EnumTest {

	public static void main(String[] args) {
		State state = State.OK;
		System.out.println(state.name());
	}

}

enum State {OK, CANCELED, ERROR}