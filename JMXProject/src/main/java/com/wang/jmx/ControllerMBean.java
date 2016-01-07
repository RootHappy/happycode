package com.wang.jmx;

public interface ControllerMBean {

	public void setName(String name);

	public String getName();

	public String status();

	public void start();

	public void stop();


}
