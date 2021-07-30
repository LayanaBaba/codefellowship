package com.example.codefellowship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


//@ComponentScan

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.spring.beans" })
@SpringBootApplication
public class CodefellowshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodefellowshipApplication.class, args);
	}

}
