package com.zhiliao.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import ch.qos.logback.classic.util.ContextInitializer;

/**
 * 日志以邮件方式发送
 * 
 * @author zhangqh
 * @date 2018年8月2日
 */
public class DemoLogReadConfig08 {
	
	public static void main(String[] args) throws InterruptedException {
		
		// 设置系统变量logback.configurationFile值  logback会读取该系统配置 后边源码解析会具体讲到这一块
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/logback08.xml");
		Logger logger = LoggerFactory.getLogger(DemoLogReadConfig08.class);
		Marker notifyAdmin = MarkerFactory.getMarker("NOTIFY_ADMIN");
		for(int i=0;i<2;i++){
			logger.error("基于标记告警的开始......{}",i);
			logger.error(notifyAdmin,"基于标记告警的开始结束.........{}",i);
			Thread.sleep(1000);
		}
		
		logger.error("我是不会发告警的error.......");
	
	}
}
