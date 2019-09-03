package com.ing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MaggieMortgageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaggieMortgageApplication.class, args);
		
	}

}
