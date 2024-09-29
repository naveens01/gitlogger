package com.example.gitintlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Return the name of your login view (Thymeleaf, JSP, etc.)
    }
}
