package com.security.demo.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.demo.entity.User;
import com.security.demo.service.impl.UserServiceImpl;

import io.jsonwebtoken.security.InvalidKeyException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserServiceImpl userService;

	@PostMapping
	public User addUser(@RequestBody User user) {

		return userService.addUser(user);
	}

	@PostMapping("/login")
	public String login(@RequestBody User user) throws InvalidKeyException, NoSuchAlgorithmException {

		return userService.authenticateUser(user);
	}
	
	@GetMapping("/list")
	public List<User> getUsers(){
		
		return userService.getUsers();
	}
}
