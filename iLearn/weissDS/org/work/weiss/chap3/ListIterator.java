package org.work.weiss.chap3;

import java.util.Iterator;

public interface ListIterator<T> extends Iterator<T>{
	boolean hasPrevious();
	T previous();
	void add(T x);
	void set(T newVal);
}
