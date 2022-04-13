package com.assignment.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.assignment.bookstore")
@EnableAutoConfiguration
public class EBookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBookstoreApplication.class, args);
	}

}
