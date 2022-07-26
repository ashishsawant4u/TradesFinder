package com.trades.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradesFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradesFinderApplication.class, args);
	}

}
