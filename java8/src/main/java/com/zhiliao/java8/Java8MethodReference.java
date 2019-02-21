package com.zhiliao.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 *
 * @author zhangqh
 * @date 2019年2月16日
 */
public class Java8MethodReference {
	
	public static void main(String[] args) {
		
		// 构造器引用
		Car car = Car.create( Car::new );
		
		// 类似于
		Car car2 = new Car();
		
		List<Car> listcars = Arrays.asList(car,car2);
		
		// 静态方法引用
		listcars.forEach(c -> {Car.collide(c,"新方式");});
		// 类似于
		for(Car c:listcars){
			Car.collide(c,"老方式");
		}
		// 特定类的任意对象的方法引用
		listcars.forEach(Car::repair);
		
		// 特定对象的方法引用
		listcars.forEach(car::follow);
		
		List names = new ArrayList();

		names.add("Google");
		names.add("Runoob");
		names.add("Taobao");
		names.add("Baidu");
		names.add("Sina");

		names.forEach(System.out::println);
	}
}
