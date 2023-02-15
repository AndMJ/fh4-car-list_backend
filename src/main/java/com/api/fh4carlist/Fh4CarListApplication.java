package com.api.fh4carlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Fh4CarListApplication {

	public static void main(String[] args) {
		SpringApplication.run(Fh4CarListApplication.class, args);
	}

}
