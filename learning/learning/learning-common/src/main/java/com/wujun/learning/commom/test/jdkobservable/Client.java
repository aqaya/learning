package com.wujun.learning.commom.test.jdkobservable;

public class Client {
	public static void main(String[] args) {
		MyObservable myObs = new MyObservable();
		MyObserver myObserver1 = new MyObserver();
		MyObserver myObserver2 = new MyObserver();
		MyObserver myObserver3 = new MyObserver();
		myObs.addObserver(myObserver1);
		myObs.addObserver(myObserver2);
		myObs.addObserver(myObserver3);
		myObs.setDate("1");
		myObs.setDate("2");
	}
}
