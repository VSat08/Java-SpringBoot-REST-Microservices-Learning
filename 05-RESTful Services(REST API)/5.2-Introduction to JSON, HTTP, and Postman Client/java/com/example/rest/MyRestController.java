package com.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyRestController {

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello Spring!";
	}

	@GetMapping("/bye")
	public String sayBye() {
		return "Bye Spring!";
	}

}
