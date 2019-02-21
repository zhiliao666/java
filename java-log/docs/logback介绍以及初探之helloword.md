
## 一、logback的介绍

Logback是由log4j创始人设计的又一个开源日志组件。logback当前分成三个模块：logback-core，logback- classic和logback-access。logback-core是其它两个模块的基础模块。logback-classic是log4j的一个改良版本。此外logback-classic完整实现SLF4J API使你可以很方便地更换成其它日志系统如log4j或JDK14 Logging。logback-access访问模块与Servlet容器集成提供通过Http来访问日志的功能。 Logback是要与SLF4J结合起来用两个组件的官方网站如下：

logback的官方网站：http://logback.qos.ch
SLF4J的官方网站：http://www.slf4j.org

## 二、logback之初探helloword

### 1,pom文件中加入所需包
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
### 2,新建DemoHelloword类如下
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
### 3，执行结果如下
```
13:28:57.010 [main] DEBUG com.zhiliao.demo.DemoHelloword - Hello world.
```

