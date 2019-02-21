package com.zhiliao.demo;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

public class AA {
	public static void main(String[] args) {
		ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory
				.getLogger(DemoHelloword.class);

		LoggerContext loggerContext = rootLogger.getLoggerContext();
		// we are not interested in auto-configuration
		loggerContext.reset();

		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setContext(loggerContext);
		encoder.setPattern("%d{YYYY-MM-dd HH:mm:ss.SSS} %r [%thread] %le %logger - %msg%n");
		encoder.setOutputPatternAsHeader(true);
		encoder.start();

		ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<ILoggingEvent>();
		appender.setContext(loggerContext);
		appender.setEncoder(encoder);
		appender.start();

		rootLogger.addAppender(appender);

		rootLogger.debug("Message 1");
		rootLogger.warn("Message 2");
	}
}
