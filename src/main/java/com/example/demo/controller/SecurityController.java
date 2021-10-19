package com.example.demo.controller;

import com.example.demo.entity.User;
//import com.example.auth.jwt.service.UserService;
import com.example.demo.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final UserServiceImpl userService;

    @GetMapping("/user/get")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
