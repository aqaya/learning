package com.wujun.learning.commom.test.jdkobservable;

import java.util.Observable;
import java.util.Observer;

public class MyObserver implements Observer {
	
	private static int seq = 1;
	
	private int id;
	
	public MyObserver(){
		id = seq++;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Observable:" + o + ",arg:" + arg + ",Obj id:" + id + " is updated!");
	}

	@Override
	public String toString() {
		return "MyObserver [id=" + id + "]";
	}

}
