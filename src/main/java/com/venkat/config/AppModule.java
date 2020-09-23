package com.venkat.config;

import org.aeonbits.owner.ConfigFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.vanillasource.jaywire.standalone.StandaloneModule;
import com.venkat.gerericDao.BaseDaoImpl;
import com.venkat.model.Employee;
import com.venkat.service.EmployeeService;

/**
 * 
 * @author Venkateswaran.T 
 *
 */
public class AppModule extends StandaloneModule {

	// config class
	public JdbcTemplate getJdbcTemplate(DriverManagerDataSource driverManager) {
		return singleton(() -> new JdbcTemplate(driverManager));
	}

	public ConfigProperty getJConfigProperty() {
		return singleton(() -> ConfigFactory.create(ConfigProperty.class));
	}

	// service class
	public BaseDaoImpl<Employee> getEmployeeService() {
		return singleton(() -> new BaseDaoImpl<Employee>());
	}

	// resource class
	public EmployeeService getEmployeeResource() {
		return singleton(() -> new EmployeeService());
	}

}
