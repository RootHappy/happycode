package com.wang.decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;


public class JavaIOTest {
	public static void main(String[] args) throws Exception {
		int c;
		InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("build.gradle")));
		while((c = in.read()) >= 0) {
			System.out.print((char)c);
		}
		in.close();
	}
}
