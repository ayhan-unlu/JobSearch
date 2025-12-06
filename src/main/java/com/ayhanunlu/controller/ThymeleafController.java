package com.ayhanunlu.controller;

import com.ayhanunlu.data.dto.AdminSessionDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
/*

    @GetMapping("/")
    public String dashboard(){
        return "/dashboard";

    }
*/

    /// GET LOGIN
    /// http://localhost:8080/login
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin_dashboard")
    public String adminDashboard(HttpSession httpSession, Model model){
        AdminSessionDto adminSessionDto = (AdminSessionDto) httpSession.getAttribute("adminSessionDto");
        model.addAttribute("adminSessionDto",adminSessionDto);
        return "admin_dashboard";
    }


}
