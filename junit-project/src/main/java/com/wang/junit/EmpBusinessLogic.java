package com.wang.junit;

public class EmpBusinessLogic {
	
	public double calculateYearlySalary(EmployeeDetails employeeDetails) {
		double yearlySalary = 0.0;
		yearlySalary = employeeDetails.getMonthSalary() * 12;
		return yearlySalary;
	}
	
	public double calculateAppraisal(EmployeeDetails employeeDetails) {
		double appraisal = 0.0;
		if( employeeDetails.getMonthSalary() < 10000) {
			appraisal = 500;
		}else {
			appraisal = 1000;
		}
		return appraisal;
	}

}
