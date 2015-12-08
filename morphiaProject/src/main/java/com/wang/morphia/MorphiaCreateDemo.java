package com.wang.morphia;

import java.util.List;

import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.mongodb.MongoClient;
import com.wang.morphia.model.Employee;

public class MorphiaCreateDemo {

	@Test
	public void createMorphia() {
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.wang.morphia.model");
		Datastore datastore =  morphia.createDatastore(new MongoClient(), "morphia_example");
		datastore.ensureIndexes();
	}

	@Test
	public void savingData() {
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.wang.morphia.model");
		Datastore datastore =  morphia.createDatastore(new MongoClient(), "morphia_example");
		final Employee elmer = new Employee("Elmer Fudd", 50000.0);
		datastore.save(elmer);
		final Employee daffy = new Employee("Daffy Duck", 40000.0);
		datastore.save(daffy);
		final Employee pepe = new Employee("Pepé Le Pew", 25000.0);
		datastore.save(pepe);
		elmer.getDirectReports().add(daffy);
		elmer.getDirectReports().add(pepe);
		datastore.save(elmer);
	}

	@Test
	public void queryData() {
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.wang.morphia.model");
		Datastore datastore =  morphia.createDatastore(new MongoClient(), "morphia_example");

		final Query<Employee> query = datastore.createQuery(Employee.class);
		List<Employee> employees =  query.asList();
		displayList(employees);
		employees = datastore.createQuery(Employee.class).field("salary").lessThanOrEq(30000).asList();
		displayList(employees);
		employees = datastore.createQuery(Employee.class).filter("salary <= ", 30000).asList();
		displayList(employees);
	}

	private void displayList(List<Employee> employees) {
		for(Employee employee : employees) {
			System.out.println(employee.toString());
		}
	}

	@Test
	public void updateData() {
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.wang.morphia.model");
		Datastore datastore =  morphia.createDatastore(new MongoClient(), "morphia_example");
		final Query<Employee> query = datastore.createQuery(Employee.class).filter("salary <=", 30000);
		UpdateOperations<Employee> updateOperations = datastore.createUpdateOperations(Employee.class).inc("salary",10000);
		datastore.update(query, updateOperations);
	}

	@Test
	public void removeData() {
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.wang.morphia.model");
		Datastore datastore =  morphia.createDatastore(new MongoClient(), "morphia_example");
		final Query<Employee> overPaidQuery = datastore.createQuery(Employee.class)
                .filter("salary >", 100000);
		datastore.delete(overPaidQuery);
	}

}
