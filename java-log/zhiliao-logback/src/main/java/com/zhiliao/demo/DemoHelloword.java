package com.zhiliao.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logback 之 helloword
 *
 * @author zhangqh
 * @date 2018年7月19日
 */
public class DemoHelloword {

	private static Logger logger = LoggerFactory.getLogger(DemoHelloword.class);
	
	public static void main(String[] args)  {
		logger.error("Hello world.");
		logger.info("Hello world.");
	}
	
	public static void testA(){
		logger.info("1");
	}
	
	public static void testB(){
		logger.info("2");
	}
}
