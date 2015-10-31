package com.wang.junit;

import org.junit.Assert;
import org.junit.Test;

public class TestEmployeeDetails {
	EmpBusinessLogic empBusinessLogic = new EmpBusinessLogic();
	EmployeeDetails employee = new EmployeeDetails();
	@Test
	public void testCalculateYearlySalary() {
		employee.setName("Rajeev");
		employee.setAge(23);
		employee.setMonthSalary(8000);
		double salary = empBusinessLogic.calculateYearlySalary(employee);
		Assert.assertEquals(96000, salary,0.0);
	}
	@Test
	public void testCalculateAppraisal() {
		employee.setName("Rajeev");
		employee.setAge(23);
		employee.setMonthSalary(8000);
		double salary = empBusinessLogic.calculateAppraisal(employee);
		Assert.assertEquals(500, salary,0.0);
	}
	
}
