package com.example.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping
public class OauthApplication {

	@GetMapping("/")
	public String index() {
		return "Hello World";
	}

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}

}
