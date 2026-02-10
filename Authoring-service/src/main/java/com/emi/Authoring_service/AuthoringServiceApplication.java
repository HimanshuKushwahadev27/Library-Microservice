package com.emi.Authoring_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages="com.emi.Authoring_service.clients")
@SpringBootApplication
public class AuthoringServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthoringServiceApplication.class, args);
	}

}
