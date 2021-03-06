package com.zhiliao.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.util.ContextInitializer;

/**
 * 日志回滚FixedWindowRollingPolicy
 * 
 * @author zhangqh
 * @date 2018年8月2日
 */
public class DemoLogReadConfig07 {
	
	public static void main(String[] args) throws InterruptedException {
		
		// 设置系统变量logback.configurationFile值  logback会读取该系统配置 后边源码解析会具体讲到这一块
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/logback07.xml");
		
		Logger logger = LoggerFactory.getLogger(DemoLogReadConfig07.class);
		for(int i=0;i<100;i++){
			
			logger.debug("socket......{}",i);
			
			Thread.sleep(1000);
			
		}
	
	}
}
