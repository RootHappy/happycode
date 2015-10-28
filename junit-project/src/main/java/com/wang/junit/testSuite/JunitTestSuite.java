package com.wang.junit.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test Suite 用来执行多个Test Case
 * */
@RunWith(Suite.class)
@SuiteClasses({TestJunit1.class,TestJunit2.class})
public class JunitTestSuite {

}
