package com.zhiliao.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiliao.config.ZhiliaoLogFactory;

public class LogTest {
	
	/**
	 * 自定义的Logger使用方式
	 */
	private static final Logger logger = (Logger) ZhiliaoLogFactory.getLogger(LogTest.class)
			.enableConsoleAppender()
			.disableFileAppender()
			.enablePatternLayout()
			.build();
	/**
	 * 默认的Logger使用方式 
	 */
//	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		Long starttime = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			logger.info("测试"+i);
		}
		Long endtime = System.currentTimeMillis();
		System.out.println("执行时间："+(endtime-starttime));
	}
}
