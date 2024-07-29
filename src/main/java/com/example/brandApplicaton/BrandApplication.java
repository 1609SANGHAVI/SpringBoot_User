package com.example.brandApplicaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//@EntityScan(basePackages = "com.example.brandApplicaton.entity")
//@EnableJpaRepositories(basePackages = "com.example.brandApplication.repository")
//@ComponentScan(basePackages ="com.example.brandApplication.service")
@SpringBootApplication
public class BrandApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrandApplication.class, args);
	}

}
