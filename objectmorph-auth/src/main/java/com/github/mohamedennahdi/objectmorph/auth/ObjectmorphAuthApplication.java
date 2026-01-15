package com.github.mohamedennahdi.objectmorph.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class ObjectmorphAuthApplication {
	public static void main(final String[] args) {
		SpringApplication.run(ObjectmorphAuthApplication.class, args);
	}
}
