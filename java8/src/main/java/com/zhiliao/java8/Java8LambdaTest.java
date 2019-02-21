package com.zhiliao.java8;

/**
 * 
 *
 * @author zhangqh
 * @date 2019年2月16日
 */
public class Java8LambdaTest {
	
	final static String preGlobal = "hello,";

	public static void main(String[] args) {
	  // 类型声明
      MathOperation addition = (int a, int b) -> a + b;
      
      MathOperation subtraction = (a,b) -> a-b;
      // 大括号中的返回语句
      MathOperation multiplication = (int a, int b) -> { return a * b; };
      
      // 没有大括号及返回语句
      MathOperation division = (int a, int b) -> a / b;
      
      System.out.println("10+5="+addition.operation(10, 5));
      System.out.println("10-5="+subtraction.operation(10, 5));
      System.out.println("10*5="+multiplication.operation(10, 5));
      System.out.println("10/5="+division.operation(10, 5));
      
      // 不允许申明和局部变量同名的参数
      int c =1;
      // MathOperation addition2 = (int a, int c) -> a + c;
      // 这行将会编译报错 不能定义int c
      
      
      // 不用括号
      GreetingService greetService1 = service ->
      System.out.println("你好，"+service);
      
      // 用括号
      GreetingService greetService2 = (service) ->
	  System.out.println("你好，"+service);
      
      greetService1.sayMessage("中国！！！");
      greetService2.sayMessage("世界！！！");
      
      
      // lambda引用外部变量必须是static 的final类型
      GreetingService greetService3 = service ->
      System.out.println(preGlobal+service); // 如果preGlobal不是final将会编译不通过
      greetService3.sayMessage("中国！！！");
      
      // lambda内部变量可以不是final类型，但是在之后是不允许修改的，也就是一种隐藏的final类型了
      
      String prePrivete = "hello,";
      GreetingService greetService4 = service ->
      System.out.println(prePrivete+service); // 如果preGlobal不是final将会编译不通过
      greetService4.sayMessage("世界！！！");
      
      // prePrivete = "测试"; 释放这行代码将会编译不通过报错
      
	}

	interface MathOperation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

}
