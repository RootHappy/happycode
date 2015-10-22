package com.wang.java.RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 远程接口的实现类，也包括服务器端
 * */
public class HelloImpl extends UnicastRemoteObject implements IHello{

	/**
	 *
	 */
	private static final long serialVersionUID = -5038133269973023617L;

	protected HelloImpl() throws RemoteException {
		super();
	}

	@Override
	public String sayHello(String name) throws RemoteException {
		return "Say Hello " + name;
	}

	public static void main(String[] args) {
		try {
			IHello hello = new HelloImpl();
			java.rmi.Naming.bind("rmi://localhost:1099/hello", hello);
			System.out.println("Ready......");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
