package com.zhiliao.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import ch.qos.logback.classic.util.ContextInitializer;

/**
 * 日志打印到数据库
 * 
 * @author zhangqh
 * @date 2018年8月2日
 */
public class DemoLogReadConfig09 {
	
	public static void main(String[] args) throws InterruptedException {
		
		// 设置系统变量logback.configurationFile值  logback会读取该系统配置 后边源码解析会具体讲到这一块
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/logback09.xml");
		Logger logger = LoggerFactory.getLogger(DemoLogReadConfig09.class);
		for(int i=0;i<2;i++){
			
			logger.error("db send......{}",i);
			
			Thread.sleep(1000);
			
		}
	
	}
}
