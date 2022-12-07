package com.requests.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, JacksonAutoConfiguration.class })
@ComponentScan(basePackages = "com.requests.backend.controllers")
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}


//	/**
//	 * Tells springboot to output all loaded beans at startup
//	 */
//	@Bean
//	public CommandLineRunner run(ApplicationContext appContext) {
//		return args -> {
//		  String[] beans = appContext.getBeanDefinitionNames();
//			Arrays.stream(beans).sorted().forEach(System.out::println);
//		};
//	}
}


