package com.Bootcamp.BankMovement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankMovementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankMovementApplication.class, args);
	}

}
