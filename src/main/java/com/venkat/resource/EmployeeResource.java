package com.venkat.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.venkat.gerericDao.Factory;
import com.venkat.model.Employee;
import com.venkat.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Venkateswaran.T 
 *
 */

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "employee", description = "Endpoint for Hello specific operations")
public class EmployeeResource {

	private EmployeeService employeeService = Factory.getModule().getEmployeeResource();

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Returns param", notes = "Returns param", response = EmployeeResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of param value", response = EmployeeResource.class) })
	public Response getEmployee(@PathParam("id") String id) {
		Employee employee = employeeService.getemployee(id);
		return Response.status(200).entity(employee).build();
	}

	//https://www.logicbig.com/tutorials/misc/jackson/json-filter-annotation.html
	@GET
	@Path("filter/{id}")
	@ApiOperation(value = "Returns param", notes = "Returns param", response = EmployeeResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of param value", response = EmployeeResource.class) })
	public Response getEmployeeFileter(@PathParam("id") String id) throws JsonProcessingException {
		Employee employee = employeeService.getemployee(id);
		
		//specifying fields to be filtered
	      SimpleFilterProvider filterProvider = new SimpleFilterProvider();
	      filterProvider.addFilter("empFilter",
	              SimpleBeanPropertyFilter.filterOutAllExcept("id", "name"));
	      ObjectMapper om = new ObjectMapper();
	      om.setFilters(filterProvider);
		return Response.status(200).entity(om.writeValueAsString(employee)).build();
	}
	
	@GET
	@Path("/employees")
	@ApiOperation(value = "Returns param", notes = "Returns param", response = EmployeeResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of param value", response = EmployeeResource.class) })
	public Response getEmployees() {
		List<Employee> employees = employeeService.getemployees();
		return Response.status(200).entity(employees).build();
	}

}