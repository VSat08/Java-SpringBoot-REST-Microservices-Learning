package com.example.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {

	@GetMapping("/date")
	public String getDate(Model model) { // Model
		model.addAttribute("date", new java.util.Date());
		return "date"; // date.html
	}

	@GetMapping("/showForm")
	public String showForm() {
		return "hello-form";
	}

	@PostMapping("/processForm")
	public String processForm(@RequestParam("studName") String name, Model model) {
		model.addAttribute("name", name.toUpperCase());
		return "result"; 	//result.html
	}
}
