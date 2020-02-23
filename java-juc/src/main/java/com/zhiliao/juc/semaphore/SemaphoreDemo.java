package com.zhiliao.juc.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static void main(String[] args) {
		
		Semaphore sp = new Semaphore(3);
		
		for(int i=0;i<10;i++) {
			
			new Student(sp,"学生"+i).start();
		}
	}
}
