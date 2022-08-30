package com.ezen.view.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Email {
	
	@GetMapping(value="/mailConfirm", produces = "application/json; charset=UTF-8")
	public String mailConfirm(@RequestParam("email")String email) {
		
		System.out.println("Email의 값은? ==================================>" + email);
		
		return email;
		
	}
	
}
