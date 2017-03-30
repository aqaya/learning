package com.wujun.learning.commom.test.exception;

import com.sun.tools.extcheck.Main;

public class ExceptionTest {
	private static int loopTimes = 1000000;
	
	public static void main(String[] args) {
		
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < loopTimes; i++) {
			try{
				testException1();
			}catch (Exception e) {
				//ignore			
			}
		}
		
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
		long t3 = System.currentTimeMillis();;
		for (int i = 0; i < loopTimes; i++) {
				test1();
		}
		
		long t4 = System.currentTimeMillis();
		System.out.println(t4 - t3);
		
	}

	private static void testException1() {
		throw new RuntimeException();
	}

	private static void testException2() {
		throw new RuntimeException();
	}
	
	private static void testException3() {
		throw new RuntimeException();
	}
	
	private static void test1() {
	}

	private static void test2() {
	}
	
	private static void test3() {
	}

}
