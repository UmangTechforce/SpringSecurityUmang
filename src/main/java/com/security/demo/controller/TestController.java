package com.security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.demo.entity.User;
import com.security.demo.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

	private final UserServiceImpl userService;

	@GetMapping
	public String getTestString() {

		return "Hello Security";
	}

	@PostMapping("/user")
	public ResponseEntity<Object> addUser(@RequestBody User user) {

		user = userService.addUser(user);

		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}

}
