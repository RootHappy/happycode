package com.wang.morphia.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity
@Indexes(
		@Index(value="salary",fields=@Field("salary"))
		)
public class Employee {
	@Id
	private ObjectId id;
	private String name;
	@Reference
	private Employee manager;
	@Reference
	private List<Employee> directReports;
	@Property("wage")
	private Double salary;

	public Employee() {

	}

	public Employee(String name , Double salary) {
		this.name = name;
		this.salary = salary;
		directReports = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "id: " + id.toString() + "\nname: " + name +"\nsalary: " + salary;
	}

	public String getName() {
		return name;
	}

	public Employee getManager() {
		return manager;
	}

	public List<Employee> getDirectReports() {
		return directReports;
	}

	public Double getSalary() {
		return salary;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public void setDirectReports(List<Employee> directReports) {
		this.directReports = directReports;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
}
