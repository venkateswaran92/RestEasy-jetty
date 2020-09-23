package com.venkat.gerericDao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.venkat.config.AppModule;
import com.venkat.config.ConfigProperty;

public class Factory {

	static AppModule module;
	static {
		module = new AppModule();
	}

	private Factory() {
	}

	public static AppModule getModule() {
		return module;
	};

	public static JdbcTemplate getJdbcTemplate() {
		ConfigProperty property = module.getJConfigProperty();
		DriverManagerDataSource driverManager = new DriverManagerDataSource();
		driverManager.setDriverClassName(property.sqlDriver());
		driverManager.setUrl(property.sqlUrl());
		driverManager.setUsername(property.sqlUser());
		driverManager.setPassword(property.sqlPass());
		return module.getJdbcTemplate(driverManager);
	}
}
