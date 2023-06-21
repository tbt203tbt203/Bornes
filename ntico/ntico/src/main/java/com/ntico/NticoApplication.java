package com.ntico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NticoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NticoApplication.class, args);
	}

}
