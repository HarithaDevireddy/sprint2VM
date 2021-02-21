package com.cg.vm.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	
	@Bean
	public Docket productApi() {
		
		//Configure Swagger and return Docket instace
		return new Docket(DocumentationType.SWAGGER_2)		
				.select().apis(RequestHandlerSelectors.basePackage("com.cg.vm.web"))
				.paths(PathSelectors.regex("/api.*"))				
				.build()
				.apiInfo(metoInfo());
	}

	private ApiInfo metoInfo() {
		// Customize the Swagger output
		ApiInfo apiInfo = new ApiInfo(
				"Vehicle Rental Management Project API", 
				"VM API Created by Team 6", 
				"1.0", 
				"Terms of Service", 
				new Contact("Team 6", "Add your website url", ""), 
				"Team 6 Licence v.1.0", 
				"", new ArrayList<>());
		return apiInfo;
	}

}