package com.ayhanunlu.controller;

import com.ayhanunlu.data.dto.AdminSessionDto;
import com.ayhanunlu.data.dto.LoginDto;
import com.ayhanunlu.data.dto.LoginResult;
import com.ayhanunlu.data.dto.RegisterDto;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.LoginResponse;
import com.ayhanunlu.enums.Role;
import com.ayhanunlu.repository.UserRepository;
import com.ayhanunlu.service.AuthenticationService;
import com.ayhanunlu.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private UserRepository userRepository;

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

        model.addAttribute("loginDto", new LoginDto());
        System.out.println("get MAPPING JUST finished");

        return "login";
    }

/*
    /// POST LOGIN
    /// http://localhost:8080/login
    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, HttpSession httpSession, Model model) {
        System.out.println("POST MAPPING JUST STARTED");
        LoginResult loginResult = authenticationService.login(loginDto);
        System.out.println("Login result: "+loginResult);

        if (loginResult.getLoginResponse() == LoginResponse.SUCCESS) {
            UserEntity userEntityFromDb = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(()-> new RuntimeException("User not found."));
            System.out.println("userEntityFromDb: "+userEntityFromDb);
            switch (loginResult.getUserEntity().getRole()) {
                case ADMIN:

                    httpSession.setAttribute("adminSessionDto", userService.getCurrentAdmin(userEntityFromDb));
                    return "redirect:/admin_dashboard";
            }
        }

        return "login";
    }
*/

/*
    @GetMapping("/admin_dashboard")
    public String adminDashboard(HttpSession httpSession, Model model) {
        AdminSessionDto adminSessionDto = (AdminSessionDto) httpSession.getAttribute("adminSessionDto");
        model.addAttribute("adminSessionDto", adminSessionDto);
        return "admin_dashboard";
    }
*/

    /// REGISTER
    /// http://localhost:8080/register
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "register";
    }


    @GetMapping("/admin_dashboard")
    public String adminDashboard(HttpSession httpSession, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        AdminSessionDto adminSessionDto = (AdminSessionDto) httpSession.getAttribute("adminSessionDto");

        if (adminSessionDto == null && userDetails != null) {
            adminSessionDto = new AdminSessionDto(null, userDetails.getUsername(), Role.ADMIN);
            httpSession.setAttribute("adminSessionDto", adminSessionDto);
        }
        model.addAttribute("adminSessionDto", adminSessionDto);
        return "admin_dashboard";
    }

    /// POST REGISTER
    ///  http://localhost:8080/register
    @PostMapping("/register")
    public String postRegister(@ModelAttribute RegisterDto registerDto, Model model) {
        System.out.println("POST REGISTER METHOD started");
        //   model.addAttribute("registerDto", new RegisterDto());
        if (userService.registerNewUser(registerDto)) {
            System.out.println("POST REGISTER METHOD finished");
            return "redirect:/login?registered=true";
        } else {
            System.out.println("POST REGISTER METHOD finished");
            model.addAttribute("errorMessage","Username already exists.");
            return "register";
        }
    }

}
