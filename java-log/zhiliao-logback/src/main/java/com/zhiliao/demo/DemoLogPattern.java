package com.zhiliao.demo;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

/**
 * logback日志格式化
 *
 * @author zhangqh
 * @date 2018年7月21日
 */
public class DemoLogPattern {
	
	public static void main(String[] args) {
		
		// 注意这边classic包中的Logger
		Logger rootLogger = (Logger) LoggerFactory.getLogger(DemoLogPattern.class);

		LoggerContext loggerContext = rootLogger.getLoggerContext();
		// 这边必须重置自动配置
		loggerContext.reset();
		
		// 自定义PatternLayoutEncoder
		/**
		 * logback默认支持的日志Pattern配置
		 * 
		 * 日志打印时间  %d|%date{时间格式化}  如：%d{YYYY-MM-dd HH:mm:ss}
		 * 相对时间   %r|%relative 
		 * 日志级别  %p|%le|%level
		 * 线程名称 %t|%thread
		 * log配置名称  %c|%lo|%logger
		 * 具体打印消息  %m|%msg|%message
		 * 日志打印的类  %C|%class 注意%C是大写C
		 * 执行方法 %M|%method
		 * 代码执行具体行 %L|%line
		 * 具体文件 %F|%file
		 * 
		 */
		String patterns ="%d{YYYY-MM-dd HH:mm:ss.SSS} - %r - %C - %M - %L - %F - [%thread] - %logger - %msg%n";
		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setContext(loggerContext);
		encoder.setPattern(patterns);
		encoder.setOutputPatternAsHeader(true);
		encoder.start();
		
		// 自定义Appender
		ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<ILoggingEvent>();
		appender.setContext(loggerContext);
		appender.setEncoder(encoder);
		appender.start();
		rootLogger.addAppender(appender);

		rootLogger.info("Message 1");
	}
}
