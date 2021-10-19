package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserServiceImpl userService) {
		return (args) -> {
			Role role1 = new Role();
			role1.setName("USER1");
			userService.saveRole(role1);
			Role role2 = new Role();
			role2.setName("USER2");
			userService.saveRole(role2);

			ArrayList<Role> roles1 = new ArrayList<>();
			roles1.add(role1);
			ArrayList<Role> roles2 = new ArrayList<>();
			roles2.add(role2);

			User user1 = new User();
			user1.setUsername("Kirill");
			user1.setPassword("1");
			user1.setRoles(roles1);
			userService.saveUser(user1);

			User user2 = new User();
			user2.setUsername("Kirill1");
			user2.setPassword("1");
			user2.setRoles(roles1);
			userService.saveUser(user2);

		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
