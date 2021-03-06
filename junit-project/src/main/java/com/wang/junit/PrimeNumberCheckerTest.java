package com.wang.junit;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
public class PrimeNumberCheckerTest {
	private Integer inputNumber;
	private Boolean expectedResult;
	private PrimeNumberChecker primeNumberChecker;
	
	@Before
	public void initialize() {
		primeNumberChecker = new PrimeNumberChecker();
	}
	
	public PrimeNumberCheckerTest(Integer inputNumber , Boolean expectedResult) {
		this.inputNumber = inputNumber;
		this.expectedResult = expectedResult;
	}
	
	@Parameters
	public static Collection<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][]{
				{ 2, true },
			         { 6, false },
			         { 19, true },
			         { 22, false },
			         { 23, true }
		});
	}
	
	 @Test
	   public void testPrimeNumberChecker() {
	      System.out.println("Parameterized Number is : " + inputNumber);
	      Assert.assertEquals(expectedResult, 
	      primeNumberChecker.validate(inputNumber));
	   }
}
