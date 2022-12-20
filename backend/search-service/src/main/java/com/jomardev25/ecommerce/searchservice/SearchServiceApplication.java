package com.jomardev25.ecommerce.searchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchServiceApplication.class, args);
		//ConfigurableApplicationContext context = SpringApplication.run(SearchServiceApplication.class, args);
		//context.getBean(SearchSuggestionController.class).loadSearchSuggestions();
	}

}
