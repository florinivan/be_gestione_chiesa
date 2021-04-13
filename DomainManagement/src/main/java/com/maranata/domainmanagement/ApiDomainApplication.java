package com.maranata.domainmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ApiDomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDomainApplication.class, args);
	}


}