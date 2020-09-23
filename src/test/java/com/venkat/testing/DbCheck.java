package com.venkat.testing;

import java.util.ArrayList;
import java.util.List;

import com.venkat.gerericDao.BaseDaoImpl;
import com.venkat.gerericDao.Factory;
import com.venkat.model.Employee;

/**
 * 
 * @author Venkateswaran.T
 *
 */

public class DbCheck {

	private static BaseDaoImpl<Employee> obj = Factory.getModule().getEmployeeService();

	static {
		try {
			obj.setClass(Employee.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// DataBase method testing
	public static void main(String args[]) throws Exception {

		obj.delete(2);
		obj.delete(1);
		
		System.out.println("save : ");
		Employee emp1 = new Employee();
		emp1.setId((1));
		emp1.setName("venkat");
		emp1.setAddress("nellikuppam");
		System.out.println("save emp:" + obj.save(emp1));
		System.out.println("");

		System.out.println("get Emp: ");
		System.out.println(obj.get(1));
		System.out.println("");

		System.out.println("Emp List: ");
		obj.query().forEach(data -> System.out.println(data));
		System.out.println("");

		System.out.println("update List: ");
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("venkat");
		emp.setAddress("cuddalore");
		System.out.println("update emp:" + obj.update(emp));
		System.out.println("");

		System.out.println("count : ");
		System.out.println("count: " + obj.getCount("where id=1", null));
		System.out.println("");

		System.out.println("delete : ");
		System.out.println("delete emp:" + obj.delete(1));
		System.out.println("");

		System.out.println("update emp with params:");
		String sql = "INSERT INTO Employee (id,name,address) VALUES(?,?,?);";
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		params.add("kumar");
		params.add("chennnai");
		System.out.println("update emp with params:" + obj.update(sql, params));
		System.out.println("");

		System.out.println("save emp with params:");
		String sqlSave = "INSERT INTO Employee (id,name,address) VALUES(?,?,?);";
		List<Object> params1 = new ArrayList<Object>();
		params1.add(2);
		params1.add("kumar");
		params1.add("chennai");
		System.out.println("save emp with params:" + obj.save(sqlSave, params1));
		System.out.println("");

		System.out.println("query with where condtion : ");
		List<Object> params2 = new ArrayList<Object>();
		params2.add("id");
		params2.add("name");
		params2.add("address");
		System.out.println("query with where condtion: " + obj.query("where id=1"));
		System.out.println("");
	}	
}
