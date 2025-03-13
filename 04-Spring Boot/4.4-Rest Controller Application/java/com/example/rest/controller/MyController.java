package com.example.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyController {

	@GetMapping("/hello")
	public String sayHello() {
		return "REST API says Hello!";
	}

	@GetMapping("/about")
	public String about() {
		return "It's all about Spring Boot!";
	}
}
