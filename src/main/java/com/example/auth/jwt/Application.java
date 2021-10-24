package com.example.auth.jwt;

import com.example.auth.jwt.entity.Booking;
import com.example.auth.jwt.entity.Roles;
import com.example.auth.jwt.entity.User;
//import com.example.auth.jwt.service.UserService;
import com.example.auth.jwt.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;

@EnableScheduling
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(UserServiceImpl userService) {
//		return (args) -> {
//
//			User user = new User();
//			user.setName("Kirill Sidorov");
//			user.setUsername("Kirill");
//			user.setRoles(Collections.singleton(Roles.ADMIN));
//			user.setBooking(new Booking());
//			user.setPassword("1");
//			userService.saveUser(user);
//		};
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
