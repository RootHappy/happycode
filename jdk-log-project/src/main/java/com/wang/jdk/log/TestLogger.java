package com.wang.jdk.log;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestLogger {

	public static void main(String[] args) throws SecurityException, IOException {
		Logger logger = Logger.getLogger("com.wang.jdk.log.TestLogger");
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.ALL);
		logger.addHandler(handler);
		FileHandler fileHandler = new FileHandler("/home/wangyunlong/log.txt");
		fileHandler.setLevel(Level.ALL);
		logger.addHandler(fileHandler);
		logger.severe("wangyunlong");
		logger.warning("wangyunong");
		logger.info("wangyunlong");
		logger.config("wangyunlong");
		logger.fine("wangyunlong");
		logger.finer("wangyunlong");
		logger.finest("finest");
	}
}
