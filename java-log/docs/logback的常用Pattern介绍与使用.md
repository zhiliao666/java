
## 一、logback的常用Pattern介绍

>日志打印时间  %d|%date{时间格式化}  如：%d{YYYY-MM-dd HH:mm:ss}  
 相对时间   %r|%relative  
 日志级别  %p|%le|%level  
 线程名称 %t|%thread  
 log配置名称  %c|%lo|%logger  
 具体打印消息  %m|%msg|%message  
 日志打印的类  %C|%class 注意%C是大写C  
 执行方法 %M|%method  
 代码执行具体行 %L|%line  
 具体文件 %F|%file  

除了这些常用的Pattern之外，其他配置可以[点击查看](https://github.com/qos-ch/logback/blob/master/logback-classic/src/main/java/ch/qos/logback/classic/PatternLayout.java)

## 二、logback之基于java手动编写Pattern演示使用

```
public static void main(String[] args) {
		
	// 注意这边classic包中的Logger
	Logger rootLogger = (Logger) LoggerFactory.getLogger(DemoLogFormat.class);

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

```

具体详情可以[点击查看](https://github.com/zhiliao666/java-log/blob/master/zhiliao-logback/src/main/java/com/zhiliao/demo/DemoLogPattern.java)


