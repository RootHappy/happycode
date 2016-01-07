package com.wang.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Test {

	public static void main(String[] args) throws Exception{
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		ControllerMBean controller = new Controller();

		mbs.registerMBean(controller, new ObjectName("MyappMBean:name=controller"));




	}

}
