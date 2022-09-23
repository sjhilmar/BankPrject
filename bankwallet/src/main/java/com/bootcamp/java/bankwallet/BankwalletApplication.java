package com.bootcamp.java.bankwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankwalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankwalletApplication.class, args);
	}

}
