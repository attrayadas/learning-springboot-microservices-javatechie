package com.attraya;

import com.attraya.di.UserApp;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Sb02DependencyInjectionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Sb02DependencyInjectionApplication.class, args);
		System.out.println("Spring Boot run method executed!");
//		UserApp userApp = context.getBean(UserApp.class);
//		userApp.loadUserFeeds();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Executing CommandLineRunner's run() method!");
		System.out.println("Establishing JDBC Connection");
	}

	@PostConstruct
	public void preInitialize(){
		System.out.println("Executing PostConstruct method");
	}
}
