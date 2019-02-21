package com.zhiliao.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Java8Stream {
	
	public static void main(String[] args) {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		
		List<String> resultList =strings.stream().filter(item -> !item.isEmpty()).collect(Collectors.toList());
		
		System.out.println(resultList);
		
		System.out.println(strings.stream().filter(item -> !item.isEmpty()).count());
		
		resultList.forEach(System.out::println);
		
		List<User> listUser = Arrays.asList(new User(1, "张三"), new User(2, "李四"),new User(2, "王麻子"));
		
		Map<Integer,List<User>> map1 = listUser.stream().collect(Collectors.groupingBy(User::getId));
		
		System.out.println(map1);
		
		Map<Integer,User> map2 = listUser.stream().collect(Collectors.toMap(User::getId, b -> b,(k1,k2) -> k2));
		
		System.out.println(map2);
		
		
		Random random = new Random();
		random.ints().limit(10).sorted().forEach(System.out::println);
	}
}
