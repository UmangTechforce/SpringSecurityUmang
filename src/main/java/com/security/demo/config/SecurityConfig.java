package com.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
		
	private final UserDetailsService userDetailsService;
	private final JwtFilter jwtFilter;
	
	
	@Bean
	public SecurityFilterChain getChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf(Customizer -> Customizer.disable());
		httpSecurity.authorizeHttpRequests(Request-> Request
			//	.requestMatchers("admin/**").hasAnyRole(Role.ADMIN.toString())
				.requestMatchers("admin/**").hasAnyRole("EMP","ADMIN")
				.requestMatchers("user", "/user/login/**","v3/**","swagger-ui/**")
				.permitAll()
				.anyRequest().authenticated());
//		httpSecurity.formLogin(Customizer.withDefaults());
//		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.sessionManagement(Session-> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
	}

	
	@Bean
	public AuthenticationProvider authenticationProvider() {
	
		//USed to perform the autheticatiin of data from data base
		
	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//	daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
	daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
	daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		
	return daoAuthenticationProvider;
	
	}
	
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
}
