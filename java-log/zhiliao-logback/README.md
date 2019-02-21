
****

# 目录

* [logback的介绍](#logback的介绍)
* [logback之配置使用](#logback之配置使用)
    * [logback之初探helloword](#logback之初探helloword)
    * [logback的常用Pattern介绍](#logback的常用Pattern介绍)
    * [logback之基于java手动编写Pattern演示使用](#logback之基于java手动编写Pattern演示使用)
    * [logback基于配置文件的使用](#logback基于配置文件的使用)
		* [基于配置文件打印日志](#基于配置文件打印日志)
		* [logback日志打印到具体file文件](#logback日志打印到具体file文件)
		* [logback根据项目运行时间生成对应的日志文件](#logback根据项目运行时间生成对应的日志文件)
		* [logback基于时间回滚日志配置](#logback基于时间回滚日志配置)
		* [logback文件大小上限设置回滚SizeAndTimeBasedRollingPolicy](#logback文件大小上限设置回滚SizeAndTimeBasedRollingPolicy)


## logback的介绍

Logback是由log4j创始人设计的又一个开源日志组件。logback当前分成三个模块：logback-core，logback- classic和logback-access。logback-core是其它两个模块的基础模块。logback-classic是log4j的一个改良版本。此外logback-classic完整实现SLF4J API使你可以很方便地更换成其它日志系统如log4j或JDK14 Logging。logback-access访问模块与Servlet容器集成提供通过Http来访问日志的功能。 Logback是要与SLF4J结合起来用两个组件的官方网站如下：

logback的官方网站：http://logback.qos.ch
SLF4J的官方网站：http://www.slf4j.org

## logback之配置使用

### logback之初探helloword

> 项目所需包引入：

```
<dependency>
	<groupId>ch.qos.logback</groupId>
	<artifactId>logback-core</artifactId>
	<version>1.2.3</version>
</dependency>

<dependency>
	<groupId>ch.qos.logback</groupId>
	<artifactId>logback-classic</artifactId>
	<version>1.2.3</version>
</dependency>

<dependency>
	<groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
	<version>1.7.25</version>
</dependency>
```

> 新建DemoHelloword类如下
```
/**
 * logback 之 helloword
 *
 * @author zhangqh
 * @date 2018年7月19日
 */
public class DemoHelloword {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(DemoHelloword.class);
		logger.debug("Hello world.");

	}
}
```
> 运行结果如下
```
13:28:57.010 [main] DEBUG com.zhiliao.demo.DemoHelloword - Hello world.
```


### logback的常用Pattern介绍

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

### logback之基于java手动编写Pattern演示使用

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


### logback基于配置文件的使用

#### 基于配置文件打印日志


>配置如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoders are assigned the type ch.qos.logback.classic.encoder.PatternLayoutEncoder 
            by default -->
        <encoder>
            <pattern>
            	%date [%thread] [%level] [%logger:%line] - %msg%n
            </pattern>
        </encoder>
    </appender>
	
    <root level="debug">
        <appender-ref ref="STDOUT" />
    </root>
    
</configuration>

```

运行演示代码具体可以[点击查看](https://github.com/zhiliao666/java-log/blob/master/zhiliao-logback/src/main/java/com/zhiliao/demo/DemoLogReadConfig01.java)

#### logback日志打印到具体file文件

>配置如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

	<property name="USER_HOME" value="user-path/" />
	
	<appender name="FILE" class="ch.qos.logback.core.FileAppender">
		<!-- 会优先在USER_HOME目录下生成对应的日志文件，如果USER_HOME属性不存在则会在logs目录下生成日志文件,可以注释USER_HOME property运行看效果 -->
		<file>${USER_HOME:-logs/}my-zhiliao.log</file>
		<encoder>
			<pattern>%date %contextName %level [%thread] %logger{36} [%file :%line] %msg%n</pattern>
		</encoder>
	</appender>
	
	<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoders are assigned the type ch.qos.logback.classic.encoder.PatternLayoutEncoder 
            by default -->
        <encoder>
            <pattern>
            	%date [%thread] [%level] [%logger:%line] - %msg%n
            </pattern>
        </encoder>
    </appender>

	<root level="debug">
		<appender-ref ref="FILE" />
		<appender-ref ref="STDOUT" />
	</root>

</configuration>

```
#### logback根据项目运行时间生成对应的日志文件

配置如下：
```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	
	<!-- 可以指定程序启动生成对应的日志文件 -->
	<timestamp key="bySecond" datePattern="yyyyMMdd'T'HHmmss"/>
	
	<appender name="FILE" class="ch.qos.logback.core.FileAppender">
		<file>logs/my-timestamp-${bySecond}.log</file>
		<encoder>
			<pattern>%date %contextName %level [%thread] %logger{36} [%file :%line] %msg%n</pattern>
		</encoder>
	</appender>
	
	<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoders are assigned the type ch.qos.logback.classic.encoder.PatternLayoutEncoder 
            by default -->
        <encoder>
            <pattern>
            	%date [%thread] [%level] [%logger:%line] - %msg%n
            </pattern>
        </encoder>
    </appender>

	<root level="debug">
		<appender-ref ref="FILE" />
		<appender-ref ref="STDOUT" />
	</root>

</configuration>

```
#### logback基于时间回滚日志配置
```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

	<appender name="FILE"
		class="ch.qos.logback.core.rolling.RollingFileAppender">
		<file>logs/my-logFile.log</file>
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<!-- daily rollover -->
			<!-- 
				logs/my-logFile.%d.log 根据时间回滚  默认的格式是根据天回滚 即和logs/my-logFile.%d{yyyy-MM-dd}.log等同
				logs/my-logFile.%d{yyyy-MM}.log 月回滚 每月一个文件
				logs/my-logFile.%d{yyyy-ww}.log 周回滚 每周一个文件
				logs/my-logFile.%d{yyyy-MM-dd_HH}.log 小时回滚 每小时一个文件
				
				logs/%d{yyyy-MM}/my-logFile.%d.log 每月一个文件夹 按天回滚
				
			另外：回滚日志支持.gz和.zip压缩回滚日志，具体使用只需后缀给成对应的就可以 如下：
				logs/my-logFile.%d.zip 按天压缩回滚日志
				
			 -->
			<fileNamePattern>logs/my-logFile.%d{yyyy-MM-dd.hhmmss}.log</fileNamePattern>

			<!-- 最多保持30天的历史日志数据   如果30天内的数据大于3GB将会按时间清除老日志文件 -->
			<maxHistory>30</maxHistory>
			<totalSizeCap>3GB</totalSizeCap>

		</rollingPolicy>

		<encoder>
			<pattern>
				%date [%thread] [%level] [%logger:%line] - %msg%n
			</pattern>
		</encoder>
	</appender>


	<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
		<!-- encoders are assigned the type ch.qos.logback.classic.encoder.PatternLayoutEncoder 
			by default -->
		<encoder>
			<pattern>
				%date [%thread] [%level] [%logger:%line] - %msg%n
			</pattern>
		</encoder>
	</appender>

	<root level="debug">
		<appender-ref ref="FILE" />
		<appender-ref ref="STDOUT" />
	</root>

</configuration>

```

#### logback文件大小上限设置回滚SizeAndTimeBasedRollingPolicy

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

	<appender name="FILE"
		class="ch.qos.logback.core.rolling.RollingFileAppender">
		<file>logs/my-logFile.log</file>
		<rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
			<!-- daily rollover -->
			<!-- 
				文件回滚格式类似上个例子配置TimeBasedRollingPolicy
				另如果文件超过设置大小会回滚再生成对应的文件，如下：
				logs/my-logFile.2018-07-24.0.log
				logs/my-logFile.2018-07-24.1.log
			 -->
			<fileNamePattern>logs/my-logFile.%d{yyyy-MM-dd.hhmm}.%i.log</fileNamePattern>
			
			<!-- 最大文件大小 -->
			<maxFileSize>200MB</maxFileSize>  

			<!-- 最多保持30天的历史日志数据   如果30天内的数据大于3GB将会按时间清除老日志文件 -->
			<maxHistory>30</maxHistory>
			<totalSizeCap>3GB</totalSizeCap>

		</rollingPolicy>

		<encoder>
			<pattern>
				%date [%thread] [%level] [%logger:%line] - %msg%n
			</pattern>
		</encoder>
	</appender>

	<root level="debug">
		<appender-ref ref="FILE" />
	</root>

</configuration>

```
#### 动态加载修改配置文件

增加如下配置即可：
><configuration scan="true"> 
  ... 
</configuration>

默认会是每分钟检测一次文件是否修改，你也可以自定义修改检测时间，如下：但是注意记得带上单位，否则单位默认会是毫秒
><configuration scan="true" scanPeriod="30 seconds" > 
  ...
</configuration>

