package com.zhiliao.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.util.ContextInitializer;

/**
 * logback支持json输出
 *
 * @author zhangqh
 * @date 2018年7月24日
 */
public class DemoLogbackJson {
	
	public static void main(String[] args) throws InterruptedException {
		
		// 设置系统变量logback.configurationFile值  logback会读取该系统配置 后边源码解析会具体讲到这一块
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/logback-json.xml");
		
		Logger logger = LoggerFactory.getLogger(DemoLogbackJson.class);
		for(int i=0;i<100;i++){
			
			logger.info("我是根据时间回滚打印日志......{}",i);
			
			Thread.sleep(1000);
			
		}
	
	}
}
