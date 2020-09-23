package com.venkat.service;

import java.util.List;

import com.venkat.gerericDao.BaseDaoImpl;
import com.venkat.gerericDao.Factory;
import com.venkat.model.Employee;

/**
 * 
 * @author Venkateswaran.T
 *
 */
public class EmployeeService {

	private static BaseDaoImpl<Employee> employeeDao = Factory.getModule().getEmployeeService();
	static {
		try {
			employeeDao.setClass(Employee.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> getemployees() {
		return employeeDao.query();
	}

	public Employee getemployee(String id) {
		return employeeDao.get(id);
	}
}
