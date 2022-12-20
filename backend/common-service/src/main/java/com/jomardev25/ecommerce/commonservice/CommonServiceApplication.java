package com.jomardev25.ecommerce.commonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CommonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonServiceApplication.class, args);
	}

}
