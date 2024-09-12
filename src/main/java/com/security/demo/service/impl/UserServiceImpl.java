package com.security.demo.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.demo.entity.User;
import com.security.demo.repository.UserRepository;
import com.security.demo.service.JwtSetting;

import io.jsonwebtoken.security.InvalidKeyException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

	
	
	
	private final UserRepository userRepository;

	private final AuthenticationManager authenticationManager;
	
	private final JwtSetting jwtSetting;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

	public User addUser(final User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	// Method to verify if the user is already logged in
	public String authenticateUser(User user) throws InvalidKeyException, NoSuchAlgorithmException {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

		if (authentication.isAuthenticated()) {
			return jwtSetting.generateToken(user.getEmail());
		} else {
			return "Login Failed";
		}

	}

	// Serice to get all users
	public List<User> getUsers() {

		return userRepository.findAll();
	}

}
