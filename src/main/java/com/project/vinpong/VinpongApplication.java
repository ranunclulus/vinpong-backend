package com.project.vinpong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VinpongApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinpongApplication.class, args);
	}

}
