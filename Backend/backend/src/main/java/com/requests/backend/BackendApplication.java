package com.requests.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, JacksonAutoConfiguration.class })
@ComponentScan(basePackages = "com.requests.backend.controllers")
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}


