package com.wang.callback;

import java.util.concurrent.TimeUnit;

public class Server {

	public void getClientMsg(CSCallback csCallback , String msg) {
		System.out.println("服务端：服务端接收到客户端发送的请求信息为：" + msg);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("服务端：数据处理成功返回成功状态 200");
		String status = "200";
		csCallback.process(status);
	}

}
