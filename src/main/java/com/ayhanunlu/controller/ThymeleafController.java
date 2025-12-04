package com.ayhanunlu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    /// GET LOGIN
    /// http://localhost:8080
    @GetMapping("/")
    public String index(){
        return "index";
    }

}
