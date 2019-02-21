package com.zhiliao.config;

import org.slf4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地存储公用Logger
 * 
 * @author zhangqh
 * @date 2018年11月17日
 */
public class ZhiliaoLogStorageUtil {
	
   private static  Map<String,Logger> loggerMap=new ConcurrentHashMap<>();
   
   public static void put(String topic,Logger logger){
       loggerMap.put(topic,logger );
   }
   public static Logger get(String logName){
       if (logName != null){
           return loggerMap.get(logName);
       }return null;
   }
}
