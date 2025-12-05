package com.ayhanunlu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

/*
    /// GET LOGIN
    /// http://localhost:8080/login
    @GetMapping("/login")
    public String index(){
        return "dashboard";
    }
*/

    @GetMapping("/")
    public String dashboard(){
        return "/dashboard";//dashboard.html is under templates folder directly
    }
}
