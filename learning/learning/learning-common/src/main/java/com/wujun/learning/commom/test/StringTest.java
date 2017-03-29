package com.wujun.learning.commom.test;

public class StringTest {
	public static void main(String[] args) {
		String s = "abc";
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			if(s.startsWith("abc")){
				doSomething(s);
			}
		}
		long t2 = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			if(s.charAt(0) == 'a' && s.charAt(1) == 'b' && s.charAt(2) == 'c'){
				doSomething(s);
			}
		}
		long t3 = System.currentTimeMillis();
		System.out.println(t2 - t1);
		System.out.println(t3 - t2);
	}
	
	public static void doSomething(String s){}
}
