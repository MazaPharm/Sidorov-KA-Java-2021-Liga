package com.example.demo.controller;

import com.example.demo.config.JwtProvider;
import com.example.demo.entity.RegistrationRequest;
import com.example.demo.entity.User;
import com.example.demo.service.AuthRequest;
import com.example.demo.service.AuthResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        u.setName(registrationRequest.getName());
        userService.saveUser(u);
        return "OK";
    }

    @PostMapping(value = "/auth", produces= MediaType.APPLICATION_JSON_VALUE)
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponse(token);
    }
}
