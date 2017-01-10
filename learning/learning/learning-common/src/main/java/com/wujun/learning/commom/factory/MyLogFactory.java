package com.wujun.learning.commom.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogFactory {
	private static final Logger ACCESS = LoggerFactory.getLogger("ACCESS");

	private static final Logger OPERATION = LoggerFactory.getLogger("OPERATION");

	private static final Logger BUSINESS = LoggerFactory.getLogger("BUSINESS");

	private static final Logger ERROR = LoggerFactory.getLogger("ERROR");

	private static final Logger DEBUG = LoggerFactory.getLogger("DEBUG");

	private static final Logger MAIL = LoggerFactory.getLogger("MAIL");

	private static final Logger JOB = LoggerFactory.getLogger("JOB");

	private static final Logger STDOUT = LoggerFactory.getLogger("STDOUT");
	
	public static Logger getLog(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	public static Logger getAccess() {
		return ACCESS;
	}

	public static Logger getOperation() {
		return OPERATION;
	}

	public static Logger getBusiness() {
		return BUSINESS;
	}

	public static Logger getError() {
		return ERROR;
	}

	public static Logger getDebug() {
		return DEBUG;
	}

	public static Logger getMail() {
		return MAIL;
	}

	public static Logger getJob() {
		return JOB;
	}

	public static Logger getStdout() {
		return STDOUT;
	}
}
