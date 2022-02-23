package com.lessayer.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	@GetMapping("/")
	public String displayHomePage() {
		
		return "index";
		
	}
	
}
