/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.dis.junit.test;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

public class JUnit4ClassRunnerLog extends SpringJUnit4ClassRunner {
	private static Logger log = Logger.getLogger(JUnit4ClassRunnerLog.class);
	static {
		try {
			Log4jConfigurer.initLogging("classpath:properties/log4j.properties");
		} catch (FileNotFoundException e) {
			log.error("Load log4j properties error ", e);
		}
	}
	public JUnit4ClassRunnerLog(Class<?> clazz) throws InitializationError {
	      super(clazz);
	}
}
