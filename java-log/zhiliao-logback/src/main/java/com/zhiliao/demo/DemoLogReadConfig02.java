package com.zhiliao.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.util.ContextInitializer;

/**
 * 基于配置文件配置日志打印到文件
 *
 * @author zhangqh
 * @date 2018年8月24日
 */
public class DemoLogReadConfig02 {
	
	public static void main(String[] args) {
		
		// 设置系统变量logback.configurationFile值  logback会读取该系统配置 在第一篇文章logback日志初探中有讲到这个
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/logback02.xml");
		
		Logger logger = LoggerFactory.getLogger(DemoLogReadConfig02.class);
		logger.debug("日志打印到文件........");
	
	}
}
