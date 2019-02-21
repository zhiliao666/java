package com.zhiliao.java8;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Java8FuntionInterface {
	
	public static void main(String[] args) {
		MyFunctionInterface aa = msg -> {System.out.println(msg); return msg;};
		
		System.out.println(aa.sayHello("你好")); 
		
		//接受两个输入参数的操作，不返回任何结果
		BiConsumer<String, String> s1 = (a,b) -> System.out.println(a+b); 
		s1.accept("你好", "中国");
		
		//接受两个输入参数的操作，返回一个结果
		BiFunction<String, String, String> s2 =(a,b) -> {return a+b;};
		System.out.println(s2.apply("你好", "世界"));
		
		BinaryOperator<Integer> s3 = (a,b) -> a+b;
		BinaryOperator<Integer> bi = BinaryOperator.minBy(Comparator.naturalOrder());
		System.out.println(s3.apply(3, 2));
		System.out.println(bi.apply(3, 2));
		
		BiPredicate<Integer, Integer> s4 = (a,b) -> a==b;
		System.out.println(s4.test(1, 2));
		
		
	}
	
}
