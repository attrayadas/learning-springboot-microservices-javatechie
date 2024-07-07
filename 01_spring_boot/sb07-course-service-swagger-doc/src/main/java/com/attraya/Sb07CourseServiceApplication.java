package com.attraya;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "COURSE SERVICE",
								version = "v 3.0",
								description = "Course API CRUD Operation :)"))
public class Sb07CourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb07CourseServiceApplication.class, args);
	}

}
