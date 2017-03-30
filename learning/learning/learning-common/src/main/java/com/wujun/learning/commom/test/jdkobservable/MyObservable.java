package com.wujun.learning.commom.test.jdkobservable;

public class MyObservable extends java.util.Observable {

	private static int seq = 1;

	private int id;

	public MyObservable() {
		id = seq++;
	}

	public void setDate(String data) {
		this.setChanged();
		this.notifyObservers(data);
	}

	@Override
	public String toString() {
		return "MyObservable [id=" + id + "]";
	}
}
