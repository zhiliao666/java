package com.zhiliao.demo;

import org.slf4j.Logger;
import static ch.qos.logback.classic.ClassicConstants.FINALIZE_SESSION_MARKER;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import ch.qos.logback.classic.util.ContextInitializer;

/**
 * SiftingAppender 分割日志文件
 * 
 * @author zhangqh
 * @date 2018年8月2日
 */
public class DemoLogReadConfig10 {
	
	public static void main(String[] args) throws InterruptedException {
		
		// 设置系统变量logback.configurationFile值  logback会读取该系统配置 后边源码解析会具体讲到这一块
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/logback10.xml");
		Logger logger = LoggerFactory.getLogger(DemoLogReadConfig10.class);
		MDC.put("userid", "zhangsan");
		for(int i=0;i<5;i++){
			if(i%2 ==1 ){
				logger.error("zhanngsan db send......{}",i);
			}else{
				MDC.put("userid", "lisi");
				logger.error("lisi db send......{}",i);
			}
			logger.info(FINALIZE_SESSION_MARKER, "About to end the job");
			
			Thread.sleep(1000);
			
		}
	
	}
}
