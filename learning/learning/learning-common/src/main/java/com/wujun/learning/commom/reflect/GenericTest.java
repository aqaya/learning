package com.wujun.learning.commom.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GenericTest<T> {
	T t;
	public GenericTest(T t) {
		this.t = t;
	}
	
	public <E> void say(E e){
		System.out.println(t.toString() + ":" + e.toString());
	}
	
	public static void main(String[] args) {
		GenericTest<String> gt = new GenericTest<String>("Hello");
		gt.say(1);
		
		Field[] fields = Person.class.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getName() + ":" + fields[i].getType().getName() + ":" + Modifier.toString(fields[i].getModifiers()));
		}
		
		Method[] methods = Person.class.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName() + ":" + methods[i].getReturnType().getName());
		}
	}
}
