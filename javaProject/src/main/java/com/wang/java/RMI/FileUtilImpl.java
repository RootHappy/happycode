package com.wang.java.RMI;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileUtilImpl extends UnicastRemoteObject implements IFileUtil{

	/**
	 *
	 */
	private static final long serialVersionUID = -2591515206943214566L;

	protected FileUtilImpl() throws RemoteException {
		super();
	}

	@Override
	public byte[] downloadFile(String fileName) throws RemoteException {
		File file = new File(fileName);
		byte buffer[] = new byte[(int)file.length()];
		int size = buffer.length;
		System.out.println("download file size = " + size + "b");
		if(size > 1024 * 1024 * 10) {
			throw new RemoteException("Error:<the file is too big!>");
		}
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
			input.read(buffer, 0, size);
			input.close();
			return buffer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
