package com.security.demo.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.demo.entity.User;
import com.security.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User userEntity = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		return org.springframework.security.core.userdetails.User.builder().username(userEntity.getEmail())
				.password(userEntity.getPassword())
				//.roles("EMP" ,"ADMIN" )
				.roles(userEntity.getRoles().stream().map(e->e.name()).toArray(String[]::new))
				.build();

		// return new UserPrincipal(userEntity); --> This is other impl of UserDetail
	}

}
