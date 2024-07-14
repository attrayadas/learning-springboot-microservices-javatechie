package com.attraya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Sb11SpringbootSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb11SpringbootSchedulerApplication.class, args);
	}

}
