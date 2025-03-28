package com.example.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/date")
	public String getDate(Model model) {	// Model 
		model.addAttribute("date", new java.util.Date());
		return "date";	// date.html
	}
}
