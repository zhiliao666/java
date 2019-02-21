
****

缘由：最近在项目开发中各模块中经常需要使用到业务日志的打印备份回滚等，以备之后做日志的分析等，项目框架使用的是springboot，但基于springboot的核心思想约定大于配置，甚是感觉项目中一些logback.xml配置之繁琐，如果你也有同样的烦恼，请看如下介绍

注：本工具类是封装在Logback的基础之上，所以在使用的时候项目必须有对应的logback依赖，并对logback之使用和原理有一定理解，如果未使用过logback建议先看[logback之详细使用和介绍](https://github.com/zhiliao666/java-log/tree/master/zhiliao-logback)


## 1,配置参数说明

>loggerName  日志名称/对应业务主题名称  
 basePath   日志存放跟路径 可以是相对路径 如当前项目目录的logs目录下 则配置为：/logs  
 backPath  日志备份回滚目录  
 fileName   日志文件名称  
 fileNamePattern 日志文件回滚条件格式Pattern  
 maxFileSize 日志文件最大大小 默认大小2MB  
 maxHistory  历史日志保留最长时间 默认30天  
 totalSizeCap 日志存储总大小限制 如： maxHistory 配置为30 totalSizeCap配置为3G 意思就是最多保持30天的历史日志数据 如果30天内的数据大于3GB将会按时间清除老日志文件   
 layout 自定义日志格式化layout 默认的为SimpleEncoderLayout  
 enablePatternLayout 是否启用PatternLayoutEncoder 默认是false  
 enableConsoleAppender 是否开启控制台打印 默认false  
 enableFileAppender 是否开启文件打印 默认true  
 encoderPattern PatternLayoutEncoder日志格式化对应的Pattern  
 
 ## 2,使用说明
 
新建测试类如下：

```
public class LogTest {
	
	/**
	 * 自定义的Logger使用方式
	 */
	private static final Logger logger = (Logger) ZhiliaoLogFactory.getLogger(LogTest.class).build();
	/**
	 * 默认的Logger使用方式
	 */
//	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		for(int i=0;i<1000;i++){
			logger.info("测试"+i);
		}
	}
}
```

运行将会在项目根目录下生成一个logs的文件夹下边就会产生一个zhiliao_log.log文件，怎样是不是超级简单跟原先logback的使用是不是几乎没区别

## 3,性能说明

大家可能会有一个疑问既然使用这么简单，那性能怎么样，光是简单也没用也必须不能对性能有影响才好，那接下来让我们来做一个测试如下：

```
public class LogTest {
	
	/**
	 * 自定义的Logger使用方式
	 */
	private static final Logger logger = (Logger) ZhiliaoLogFactory.getLogger(LogTest.class)
			.enableConsoleAppender()
			.disableFileAppender()
			.enablePatternLayout()
			.build();
	/**
	 * 默认的Logger使用方式 
	 */
//	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		Long starttime = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			logger.info("测试"+i);
		}
		Long endtime = System.currentTimeMillis();
		System.out.println("执行时间："+(endtime-starttime));
	}
}
```

使用自定义的Logger方式运行三次时间分别是：440，347，298  平均时间为：361.66  
使用默认的Logger方式运行三次时间分别是：367，352,455 平均时间为：391.33  

从以上结果我们可以发现自定义的方式平均时间反而比默认时间还要短一些，从原理分析来上以上两种用法性能其实是没有任何区别，
但是如果你的业务上仅仅只是想打印简单的message信息，那么可以使用自定义的打印将会有比较大的性能提升如下：

把Logger改成如下
```
public class LogTest {
	
	/**
	 * 自定义的Logger使用方式
	 */
	private static final Logger logger = (Logger) ZhiliaoLogFactory.getLogger(LogTest.class)
			.build();
	/**
	 * 默认的Logger使用方式 
	 */
//	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		Long starttime = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			logger.info("测试"+i);
		}
		Long endtime = System.currentTimeMillis();
		System.out.println("执行时间："+(endtime-starttime));
	}
}
```

再运行你会发现性能会有比较明显的提升，平均大概都在100多毫秒，为什么性能会比较大的提升，简单来讲就是省去了很多不需要的日志格式化打印，不明白的可以看之前那几篇关于logback介绍的文章


## 4,springboot中如何使用该工具类

1，springboot项目这边就不介绍了
2，在pom文件中应用如下配置，这边提供的是本人的maven私服
```
<repositories>
	 	 <repository>
	        <id>public</id>
	        <name>Team Maven Repository</name>
	        <url>http://111.231.119.223:8081/nexus/content/repositories/releases/</url>
	        <releases>
	            <enabled>true</enabled>
	        </releases>
	        <snapshots>
	            <enabled>true</enabled>
	        </snapshots>
	    </repository>
</repositories>

<dependency>
  <groupId>com.zhiliao</groupId>
  <artifactId>zhiliao-logback-util</artifactId>
  <version>1.0.0</version>
</dependency>
```

这样就可以直接在项目中按如下方式使用了

>private static final Logger logger = (Logger) ZhiliaoLogFactory.getLogger(LogTest.class)  
			&emsp;.enableConsoleAppender() // 这些配置规矩项目需要使用  
			&emsp;.disableFileAppender()  
			&emsp;.enablePatternLayout()  
			&emsp;.build();