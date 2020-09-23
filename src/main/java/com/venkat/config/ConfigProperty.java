package com.venkat.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "file:server.properties" })
public interface ConfigProperty extends Config {

	String sqlUser();

	String sqlPass();

	String sqlUrl();

	String sqlDriver();

}
