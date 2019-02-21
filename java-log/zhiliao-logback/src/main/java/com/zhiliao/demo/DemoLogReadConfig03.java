package com.zhiliao.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.util.ContextInitializer;

/**
 * 根据程序启动时间生成日志文件
 *
 * @author zhangqh
 * @date 2018年7月24日
 */
public class DemoLogReadConfig03 {
	
	public static void main(String[] args) throws InterruptedException {
		
		// 设置系统变量logback.configurationFile值  logback会读取该系统配置 后边源码解析会具体讲到这一块
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/logback03.xml");
		
		Logger logger = LoggerFactory.getLogger(DemoLogReadConfig03.class);
		for(int i=0;i<100;i++){
			
			logger.info("我是根据时间启动打印日志......{}",i);
			
			Thread.sleep(1000);
			
		}
	
	}
}
