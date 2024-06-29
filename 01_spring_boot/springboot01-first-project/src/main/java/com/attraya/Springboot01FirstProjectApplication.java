package com.attraya;

import com.attraya.condition.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Demo project of how Spring uses multiple conditional statements in auto-configuration
 * Path to check autoconfigured classes: org\springframework\boot\spring-boot-autoconfigure\3.3.1\spring-boot-autoconfigure-3.3.1.jar!\META-INF\spring\org.springframework.boot.autoconfigure.AutoConfiguration.imports
 *
 */

/**
 * @Configuration: Java-based config approach to define a bean
 * @AutoConfiguration: Scans pom.xml and enable/disable bean based on configuration
 * @ComponentScan: Tells SpringBoot what all class/package to be scanned
 */

/**
 * Internal working of SpringBoot:
 * 1. Create ApplicationContext
 * 2. Check Application type (Servlet or Reactive)
 * 3. Add scanned bean to application context
 * 4. Enable embedded tomcat container
 */

@SpringBootApplication
public class Springboot01FirstProjectApplication implements CommandLineRunner {

	@Autowired
	private DataSourceConfig dataSourceConfig;

	public static void main(String[] args) {
		SpringApplication.run(Springboot01FirstProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataSourceConfig.makeConnection();
	}
}
