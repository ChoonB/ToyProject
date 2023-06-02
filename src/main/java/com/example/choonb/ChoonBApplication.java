package com.example.choonb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ChoonBApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoonBApplication.class, args);
	}

}
