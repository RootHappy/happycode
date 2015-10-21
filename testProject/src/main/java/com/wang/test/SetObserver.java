package com.wang.test;

public interface SetObserver<E> {
	void added(ObservableSet<E> set ,E element);
}
