package com.richer.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages={"com.richer.insurance"})
public class InsuranceTrackerApplication {
	public static void main(String[] args) {
		SpringApplication.run(InsuranceTrackerApplication.class, args);
	}
}
