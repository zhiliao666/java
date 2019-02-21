package com.zhiliao.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.util.ContextInitializer;

/**
 * logback 之 helloword
 *
 * @author zhangqh
 * @date 2018年7月19日
 */
public class MyLayoutConfig {

	
	public static void main(String[] args)  {
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/myLayoutConfig.xml");
		Logger logger = LoggerFactory.getLogger(MyLayoutConfig.class);
		logger.error("Hello world.");
		logger.info("Hello world.");
	}
	
}
