package com.zhiliao.config;
import org.slf4j.LoggerFactory;

import com.zhiliao.layout.SimpleEncoderLayout;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;

/**
 * 日志创建工厂类
 *
 * @author zhangqh
 * @date 2018年11月15日
 */
public class ZhiliaoLogFactory {
	
	
	public static LogBuilder getLogger(Class<?> clazz){
		return getLogger(clazz.getName());
	}

	public static LogBuilder getLogger(String loggerName) {
		return new LogBuilder(loggerName);
	}
	
	public static class LogBuilder {
		
		private final String DEFAULT_ENCODER_PATTERN = "%d{HH:mm:ss.SSS} [%level] [%logger] - %msg%n" ;
		
		private final Boolean DEFAULT_ENABLE_PATTERNLAYOUT = false;
		
		private final Boolean DEFAULT_ENABLE_CONSOLEAPPENDER = false;
		
		private final Boolean DEFAULT_ENABLE_FILEEAPPENDER = true;

		/**
		 * 日志名称/对应业务主题名称
		 */
		private String loggerName;

		/**
		 * 日志存放跟路径 可以是相对路径 如当前项目目录的logs目录下 则配置为：/logs
		 */
		private String basePath;

		/**
		 * 日志备份回滚目录
		 */
		private String backPath;

		/**
		 * 日志文件名称
		 */
		private String fileName;

		/**
		 * 日志文件回滚条件格式Pattern
		 */
		private String fileNamePattern;

		/**
		 * 日志文件最大大小 默认大小2MB
		 */
		private FileSize maxFileSize;

		/**
		 * 历史日志保留最长时间
		 */
		private int maxHistory;

		/**
		 * 日志存储总大小限制 如： maxHistory 配置为30 totalSizeCap配置为3G 意思就是最多保持30天的历史日志数据
		 * 如果30天内的数据大于3GB将会按时间清除老日志文件
		 */
		private FileSize totalSizeCap;

		/**
		 * 自定义日志格式化layout 默认的为SimpleEncoderLayout
		 */
		private Layout<ILoggingEvent> layout;
		
		/**
		 * 是否开启控制台打印 默认false
		 */
		private Boolean enableConsoleAppender = this.DEFAULT_ENABLE_CONSOLEAPPENDER;
		
		/**
		 * 是否开启文件打印 默认true
		 */
		private Boolean enableFileAppender = this.DEFAULT_ENABLE_FILEEAPPENDER;
		
		/**
		 * 是否启用PatternLayoutEncoder
		 */
		private Boolean enablePatternLayout = this.DEFAULT_ENABLE_PATTERNLAYOUT;

		/**
		 * PatternLayoutEncoder日志格式化对应的Pattern
		 */
		private String encoderPattern = this.DEFAULT_ENCODER_PATTERN;
		
		private LogBuilder(String loggerName) {
			this.loggerName = loggerName;
			this.basePath = "logs";
			this.fileName = "zhiliao_log";
			this.fileNamePattern = "%d{yyyy-MM-dd}.%i";
			this.maxFileSize = FileSize.valueOf("2MB");
			this.maxHistory = 30;
			this.backPath = "back";
			this.totalSizeCap = FileSize.valueOf("3GB");
			// this.encoder = new PatternLayoutEncoder();
			// this.encoderPattern = "%msg%n";
			this.layout = new SimpleEncoderLayout();
		}
		
		public LogBuilder withBackPath(String backPath) {
			this.backPath = backPath;
			return this;
		}

		public LogBuilder withBasePath(String basePath) {
			this.basePath = basePath;
			return this;
		}

		public LogBuilder withFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public LogBuilder withFileNamePattern(String fileNamePattern) {
			this.fileNamePattern = fileNamePattern;
			return this;
		}

		public LogBuilder withMaxFileSize(FileSize maxFileSize) {
			this.maxFileSize = maxFileSize;
			return this;
		}

		public LogBuilder withMaxHistory(int maxHistory) {
			this.maxHistory = maxHistory;
			return this;
		}

		public LogBuilder withTotalSizeCap(FileSize totalSizeCap) {
			this.totalSizeCap = totalSizeCap;
			return this;
		}

		public LogBuilder withLayout(Layout<ILoggingEvent> layout) {
			this.layout = layout;
			return this;
		}

		public LogBuilder enableConsoleAppender() {
			this.enableConsoleAppender = true;
			return this;
		}
		
		public LogBuilder disableFileAppender() {
			this.enableFileAppender = false;
			return this;
		}
		
		public LogBuilder enablePatternLayout() {
			this.enablePatternLayout = true;
			return this;
		}

		public LogBuilder withEncoderPattern(String encoderPattern) {
			this.encoderPattern = encoderPattern;
			return this;
		}

		public Logger build() {

			Logger rootLogger = (Logger) LoggerFactory
					.getLogger(this.loggerName);

			LoggerContext loggerContext = rootLogger.getLoggerContext();
			// 重置自动配置
			loggerContext.reset();
			
			if(enableFileAppender){
				rootLogger.addAppender(getRollingFileAppender(loggerContext));
			}
			
			if(enableConsoleAppender){
				rootLogger.addAppender(getConsoleAppender(loggerContext));
			}

			return rootLogger;
		}

		private RollingFileAppender<ILoggingEvent> getRollingFileAppender(
				LoggerContext loggerContext) {
			RollingFileAppender<ILoggingEvent> rollingFileAppender = new RollingFileAppender<ILoggingEvent>();
			rollingFileAppender.setContext(loggerContext);
			rollingFileAppender.setFile(basePath + "/" + fileName + ".log");

			rollingFileAppender.setRollingPolicy(getRollingPolicy(
					loggerContext, rollingFileAppender));
			rollingFileAppender.setEncoder(getLayoutEncoder(loggerContext));
			rollingFileAppender.start();
			return rollingFileAppender;
		}
		
		private ConsoleAppender<ILoggingEvent> getConsoleAppender(
				LoggerContext loggerContext) {
			ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<ILoggingEvent>();
			consoleAppender.setContext(loggerContext);
			consoleAppender.setEncoder(getLayoutEncoder(loggerContext));
			consoleAppender.start();
			return consoleAppender;
		}
		
		private Encoder<ILoggingEvent> getLayoutEncoder(
				LoggerContext loggerContext) {
			if (enablePatternLayout) {
				PatternLayoutEncoder encoder = new PatternLayoutEncoder();
				encoder.setPattern(encoderPattern);
				encoder.setContext(loggerContext);
				encoder.start();
				return encoder;
			} else {
				LayoutWrappingEncoder<ILoggingEvent> encoder = new LayoutWrappingEncoder<ILoggingEvent>();
				encoder.setLayout(layout);
				encoder.setContext(loggerContext);
				encoder.start();
				return encoder;
			}
		}

		private SizeAndTimeBasedRollingPolicy<ILoggingEvent> getRollingPolicy(
				LoggerContext loggerContext,
				RollingFileAppender<ILoggingEvent> rollingFileAppender) {
			SizeAndTimeBasedRollingPolicy<ILoggingEvent> sizeAndTimerollingPolicy = new SizeAndTimeBasedRollingPolicy<ILoggingEvent>();
			sizeAndTimerollingPolicy.setFileNamePattern(basePath + "/"
					+ backPath + "/" + fileName + fileNamePattern + ".log");
			sizeAndTimerollingPolicy.setMaxFileSize(maxFileSize);
			// 最多保持30天的历史日志数据 如果30天内的数据大于3GB将会按时间清除老日志文件
			sizeAndTimerollingPolicy.setMaxHistory(maxHistory);
			sizeAndTimerollingPolicy.setTotalSizeCap(totalSizeCap);
			sizeAndTimerollingPolicy.setParent(rollingFileAppender);
			sizeAndTimerollingPolicy.setContext(loggerContext);
			sizeAndTimerollingPolicy.start();
			return sizeAndTimerollingPolicy;
		}
	}

}
