package com.security.demo.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@GetMapping
	public ResponseEntity<Object> welcomeAdmin(Principal principal){
		
		return new ResponseEntity<>("Hello Admin "+principal.getName(),HttpStatus.ACCEPTED);
	}
}
