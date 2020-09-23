package com.venkat.client;

import java.io.IOException;
import java.util.List;

import com.venkat.model.Employee;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;


/**
 * @author Venkateswaran.T 
 */

//https://github.com/OpenFeign/feign

public final class ServiceClient {

public static final String API_URL = "http://localhost:8080/api/";

	public interface EmployeeInterface {
		@GET("employee/employees")
		Call<List<Employee>> employees();
	}

public static void main(String[] args) throws IOException {
	
	Retrofit retrofit = new Retrofit
				       .Builder().baseUrl(API_URL)
				       .addConverterFactory(GsonConverterFactory.create())
				       .build();

		EmployeeInterface employeeInterface = retrofit.create(EmployeeInterface.class);
		Call<List<Employee>> call = employeeInterface.employees();

		Response<List<Employee>> response=call.execute();
		
		System.out.println("Status code: "+ response.code());
		
		List<Employee> employees = response.body();
		for (Employee employee : employees) 
		{
			System.out.println(employee.getName() + " (" + employee.getAddress() + ")");
		}
	}
}