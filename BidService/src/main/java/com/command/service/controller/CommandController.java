package com.command.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {

	@GetMapping("/hello")
	public String print() {
		return "Hello";
	}
}
