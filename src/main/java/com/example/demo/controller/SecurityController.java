package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class SecurityController {

    @GetMapping("/user/get")
    public String getUser() {
        return "Hi user";
    }

}
