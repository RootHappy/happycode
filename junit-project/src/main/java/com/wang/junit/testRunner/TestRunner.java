package com.wang.junit.testRunner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.wang.junit.TestJunitFailure;

/**
 * Runner 用来运行Test Case
 * */
public class TestRunner {

	public static void main(String[] args) {
//		Result result = JUnitCore.runClasses(TestJunitSuccess.class);
		Result result = JUnitCore.runClasses(TestJunitFailure.class);
		for(Failure failure : result.getFailures())
			System.out.println(failure.toString());
		System.out.println(result.wasSuccessful());
	}

}
