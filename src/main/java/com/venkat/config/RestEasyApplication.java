package com.venkat.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.venkat.resource.EmployeeResource;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * 
 * @author Venkateswaran.T 
 *
 */

public class RestEasyApplication extends Application {

	HashSet<Object> singletons = new HashSet<Object>();

	public RestEasyApplication() {
		configureSwagger();
	}

	@Override
	public Set<Class<?>> getClasses() {

		HashSet<Class<?>> set = new HashSet<Class<?>>();

		set.add(EmployeeResource.class);

		set.add(io.swagger.jaxrs.listing.ApiListingResource.class);
		set.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

		return set;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	private void configureSwagger() {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/api");
		beanConfig.setResourcePackage(EmployeeResource.class.getPackage().getName());
		beanConfig.setTitle("RESTEasy, Embedded Jetty");
		beanConfig.setDescription("Swagger and Swagger UI");
		beanConfig.setScan(true);
	}
}
