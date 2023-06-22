package com.m2i.cda.lille;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LilleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LilleApplication.class, args);
	}

}
