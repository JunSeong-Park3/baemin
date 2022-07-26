package com.han.delivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.han.delivery.config.auth.LoginSuccess;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	//추가
	@Autowired
	private LoginSuccess loginSuccess;
	
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/", "/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest()
			.permitAll()
			.and()
			.formLogin()
			//기존
//			.loginPage("/auth/signin")
//			.loginProcessingUrl("/auth/signin") 
//			.defaultSuccessUrl("/home")
//			.failureUrl("/auth/failed");
			// 7/22 추가
			.loginPage("/auth/signin")
			.loginProcessingUrl("/auth/signin")
			.successHandler(loginSuccess)
			.failureUrl("/auth/failed");
	}
	
}