package com.wang.java.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 远程接口
 * */
public interface IHello extends Remote{
	public String sayHello(String name) throws RemoteException;
}
