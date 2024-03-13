package com.projet.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ProjetRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetRestaurantApplication.class, args);
	}

}
