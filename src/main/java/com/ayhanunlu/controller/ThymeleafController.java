package com.ayhanunlu.controller;

import com.ayhanunlu.data.dto.AdminSessionDto;
import com.ayhanunlu.data.dto.LoginDto;
import com.ayhanunlu.data.dto.LoginResult;
import com.ayhanunlu.enums.LoginResponse;
import com.ayhanunlu.service.AuthenticationService;
import com.ayhanunlu.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.ayhanunlu.enums.Role.ADMIN;

@Controller
public class ThymeleafController {
    private final UserServiceImpl userService;
    @Autowired
    AuthenticationService authenticationService;

    public ThymeleafController(UserServiceImpl userService) {
        this.userService = userService;
    }

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
    public String login(Model model) {
        System.out.println("get MAPPING JUST STARTED");

        model.addAttribute("loginDto",new LoginDto());
        System.out.println("get MAPPING JUST finished");

        return "login";
    }

    /// POST LOGIN
    /// http://localhost:8080/login
    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, HttpSession httpSession, Model model) {
        System.out.println("POST MAPPING JUST STARTED");
        LoginResult loginResult = authenticationService.login(loginDto);
        System.out.println("Login result: "+loginResult);

        if (loginResult.getLoginResponse() == LoginResponse.SUCCESS) {
            switch (loginResult.getUserEntity().getRole()) {
                case ADMIN:
                    httpSession.setAttribute("adminSessionDto", userService.getCurrentAdmin(loginResult.getUserEntity()));
                    return "redirect:/admin_dashboard";
            }
        }

        return "login";
    }

    @GetMapping("/admin_dashboard")
    public String adminDashboard(HttpSession httpSession, Model model) {
        AdminSessionDto adminSessionDto = (AdminSessionDto) httpSession.getAttribute("adminSessionDto");
        model.addAttribute("adminSessionDto", adminSessionDto);
        return "admin_dashboard";
    }


}
