package com.maranata.apibudgetplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EntityScan("com.maranata.commonbean.budgetplanner.entity")
@EnableFeignClients
@SpringBootApplication
public class ApiBudgetPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBudgetPlannerApplication.class, args);
	}

}
