package com.maranata.domainmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EntityScan("com.maranata.commonbean.management.entity")
@SpringBootApplication
public class ApiDomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDomainApplication.class, args);
	}


}
