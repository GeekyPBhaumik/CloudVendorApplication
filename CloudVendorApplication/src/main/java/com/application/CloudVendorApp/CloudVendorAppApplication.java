package com.application.CloudVendorApp;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@SpringBootApplication
public class CloudVendorAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudVendorAppApplication.class, args);
	}	
	//We are building a Docket bean in order to do the customization
	//for Swagger configuration
	@Bean
	Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_12)
				   .select()
				   .paths(PathSelectors.ant("/cloudvendor/*"))
				   .apis(RequestHandlerSelectors.basePackage("com.application.CloudVendorApp"))
				   .build()
				   .apiInfo(apiCustomData());
	}	
	 private ApiInfo apiCustomData() {
	        return new ApiInfo(
	                "Cloud Vendor API Application",
	                "Cloud Vendor Documentation",
	                "1.0",
	                "Terms of service URL",
	                new Contact("Pushpan Bhaumik", "https://www.google.com", "pushpanbhaumik200@gmail.com"),
	                "Cloud Vendor License",
	                "License URL",
	                Collections.emptyList()
	        );
	    }
	
}
