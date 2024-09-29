package com.example.gitintlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard() {
        // Return the name of the view to be rendered
        return "dashboard"; // Make sure there is a dashboard.html or dashboard.jsp in your templates directory
    }
}
