package com.wang.spring.DI;

public class Stage {
	private Stage() {

	}

	//延迟加载实例
	private static class StageSingletonHolder {
		static Stage instance = new Stage();
	}

	//返回实例
	private static Stage getInstance() {
		return StageSingletonHolder.instance;
	}

}
