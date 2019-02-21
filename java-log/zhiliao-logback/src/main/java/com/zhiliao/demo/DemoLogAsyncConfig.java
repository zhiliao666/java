package com.zhiliao.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.util.ContextInitializer;

/**
 * 异步打印日志
 *
 * @author zhangqh
 * @date 2018年7月24日
 */
public class DemoLogAsyncConfig {
	
	public static void main(String[] args) {
		
		// 设置系统变量logback.configurationFile值  logback会读取该系统配置 后边源码解析会具体讲到这一块
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/logback-async.xml");
		
		Logger logger = LoggerFactory.getLogger(DemoLogAsyncConfig.class);
		for(int i=1;i<100;i++){
			logger.debug("我是异步打印的日志{}........",i);
		}
		System.out.println("执行完毕");
	
	}
}
