package com.example.gitintlog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaviconController {
    @GetMapping("favicon.ico")
    public ResponseEntity<Void> returnNoFavicon() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 No Content
    }
}
