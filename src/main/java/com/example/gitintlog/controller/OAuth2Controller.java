package com.example.gitintlog.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

    @GetMapping("/login/oauth2/code/github")
    public String handleOAuth2Redirect(@RequestParam("code") String code) {
        // Here you can use the code to get the access token
        return "Authorization code: " + code; // For testing purposes
    }
}
