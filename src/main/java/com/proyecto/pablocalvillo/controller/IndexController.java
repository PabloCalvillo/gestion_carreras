package com.proyecto.pablocalvillo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
	private static final String INDEX = "index.html";
	
	@GetMapping("/index")
	public String index() {
		return INDEX;
	}

}
