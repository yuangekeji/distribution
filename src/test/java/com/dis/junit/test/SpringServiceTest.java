/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.dis.junit.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(JUnit4ClassRunnerLog.class)
@ContextConfiguration(locations={"classpath:config/applicationContext.xml","classpath:config/dataStorage-config.xml"})
public class SpringServiceTest {
	
}
