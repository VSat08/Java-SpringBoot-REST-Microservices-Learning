package com.example.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MVCController { // Controller

	@GetMapping("/")
	public String welcome() {
		return "welcome.html"; // View
	}

}
