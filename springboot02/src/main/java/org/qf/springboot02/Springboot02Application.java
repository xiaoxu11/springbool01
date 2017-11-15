package org.qf.springboot02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Springboot02Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot02Application.class, args);
	}
}
