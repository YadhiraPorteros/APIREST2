package com.example.APIREST2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.APIREST2")
@EnableJpaRepositories(basePackages = "com.example.APIREST2.repositories")
public class Apirest2Application {
	public static void main(String[] args) {
		SpringApplication.run(Apirest2Application.class, args);
	}
}

