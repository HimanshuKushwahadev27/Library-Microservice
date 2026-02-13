package com.emi.Catalog_Service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().permitAll()
			).build()
			;
	}
	
	@Bean
	OpenAPI openApiConfig() {
		return new OpenAPI()
				.info(new Info()
						.title("Catalog Service API")
						.description("API documentation for Catalog-service")
						.version("2.0")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org")
								))
				.externalDocs(new ExternalDocumentation()
						.description("Link to external documentation")
						.url("https://Catalog-demo.com/docs"));
	}
}
