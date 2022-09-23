package com.Bootcamp.BankClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

//@ComponentScan(basePackages = "com.BootCamp.Client.service")
//@EntityScan(basePackages = "com.BootCamp.Client.*")


public class BankClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankClientApplication.class, args);
	}

}
