package com.maranata.domainbudgetplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.maranata.commonbean.budgetplanner.entity")
@SpringBootApplication
public class BudgetPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetPlannerApplication.class, args);
	}

}
