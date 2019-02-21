package com.zhiliao.demo.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocketServer {

	private static ServerSocket ss;

	public static void main(String[] args) throws IOException {
		ss = new ServerSocket(10086);
		System.out.println("服务器正常启动。。。");
		Socket s = ss.accept();// 阻塞方法
		System.out.println("连接成功" + s.getRemoteSocketAddress());
		InputStreamReader inread = new InputStreamReader(s.getInputStream(),"UTF-8");
		BufferedReader br = new BufferedReader(inread);
		while (true) {
			String string = br.readLine();
			System.out.println("Server读到：" + string);
		}
	}
}
