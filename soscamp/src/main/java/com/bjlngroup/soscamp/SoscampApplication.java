package com.bjlngroup.soscamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SoscampApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoscampApplication.class, args);
	}

}
