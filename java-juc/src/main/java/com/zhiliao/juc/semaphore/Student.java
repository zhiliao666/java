package com.zhiliao.juc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Student extends Thread{
	
	private Semaphore sp;
	
	private String name;
	
	public Student(Semaphore sp,String name){
		this.sp = sp ;
		this.name = name ; 
	}
	public void run() {
		try {
			sp.acquire();
			System.out.println(name+"开始打饭。。。 ");
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sp.release();
			System.out.println(name+"打饭完成。。。 ");
		}
		
	}
}
