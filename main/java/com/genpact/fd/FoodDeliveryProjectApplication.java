package com.genpact.fd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.genpact.fd")
public class FoodDeliveryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryProjectApplication.class, args);
	}

}
