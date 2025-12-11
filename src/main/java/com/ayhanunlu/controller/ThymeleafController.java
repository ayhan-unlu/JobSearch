package com.ayhanunlu.controller;

import com.ayhanunlu.data.dto.JobSeekerDto;
import com.ayhanunlu.data.dto.SessionDto;
import com.ayhanunlu.data.dto.LoginDto;
import com.ayhanunlu.data.dto.RegisterDto;
import com.ayhanunlu.data.entity.JobSeekerEntity;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.Role;
import com.ayhanunlu.repository.JobSeekerRepository;
import com.ayhanunlu.repository.UserRepository;
import com.ayhanunlu.repository.VacancyRepository;
import com.ayhanunlu.service.AuthenticationService;
import com.ayhanunlu.service.impl.JobSeekerServiceImpl;
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

import java.util.List;

@Controller
public class ThymeleafController {
    private final UserServiceImpl userService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @Autowired
    private JobSeekerServiceImpl jobSeekerService;
    @Autowired
    private VacancyRepository vacancyRepository;

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
        SessionDto adminSessionDto = (SessionDto) httpSession.getAttribute("adminSessionDto");
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

    /// ADMIN DASHBOARD
    /// http://localhost:8080/admin_dashbaord

    @GetMapping("/admin_dashboard")
    public String adminDashboard(HttpSession httpSession, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        SessionDto sessionDto = (SessionDto) httpSession.getAttribute("adminSessionDto");

        if (sessionDto == null && userDetails != null) {
//            sessionDto = new SessionDto(null, userDetails.getUsername(), Role.ADMIN);
            UserEntity adminEntity = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
            sessionDto = userService.createSessionDto(adminEntity);
            httpSession.setAttribute("adminSessionDto", sessionDto);
        }
        List<JobSeekerEntity> allJobSeekerEntitiesList = jobSeekerService.getAllJobSeekerEntities();
//        JobSeekerEntity jobSeekerEntity = new JobSeekerEntity();
        model.addAttribute("adminSessionDto", sessionDto);
//        model.addAttribute("jobSeekerEntity",jobSeekerEntity);
        model.addAttribute("jobSeekerEntityList",allJobSeekerEntitiesList);
        return "admin_dashboard";
    }

    /// MILITARY SERVICE FILTER
    /// http://localhost:8080/admin_dashboard
    @GetMapping("/admin_dashboard/military_service_filter")
    public String adminDashboardMilitaryServiceFilter(HttpSession httpSession, Model model ,@AuthenticationPrincipal UserDetails userDetails){
        SessionDto sessionDto = (SessionDto) httpSession.getAttribute("adminSessionDto");
        model.addAttribute("adminSessionDto",sessionDto);

        List<JobSeekerEntity> militaryServiceFilteredJobSeekerEntityList=jobSeekerService.getJobSeekerEntitiesFinishedMilitaryService();
        model.addAttribute("jobSeekerEntityList",militaryServiceFilteredJobSeekerEntityList);

        return "admin_dashboard";
    }

    /// 5 EXPERIENCE YEAR FILTER
    /// http://localhost:8080/admin_dashboard
    @GetMapping("/admin_dashboard/five_experience_year_filter")
    public String adminDashBoardFiveExperienceYearFilter(HttpSession httpSession, Model model, @AuthenticationPrincipal UserDetails userDetails){
        SessionDto sessionDto = (SessionDto) httpSession.getAttribute("adminSessionDto");
        model.addAttribute("adminSessionDto",sessionDto);

        List<JobSeekerEntity> fiveExperienceYearFilteredJobSeekerEntityList=jobSeekerService.getJobSeekerEntitiesHaveExperience5PlusYears();
        model.addAttribute("jobSeekerEntityList",fiveExperienceYearFilteredJobSeekerEntityList);
        return "admin_dashboard";
    }

    /// USER DASHBOARD
    /// http://localhost:8080/user_dashboard
    @GetMapping("/user_dashboard")
    public String userDashBoard(HttpSession httpSession, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        SessionDto sessionDto = (SessionDto) httpSession.getAttribute("userSessionDto");

        if(sessionDto == null){
            sessionDto = userService.createSessionDto(userEntity);
            httpSession.setAttribute("userSessionDto",sessionDto);
        }

//        if (sessionDto == null && userDetails != null) {
//            UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
////            sessionDto = new SessionDto(userEntity.getId(), userEntity.getUsername(), userEntity.getRole(), userEntity.getStatus(), userEntity.getFailedLoginAttempts());
////            sessionDto = new SessionDto(null,userDetails.getUsername(),Role.USER);
//            sessionDto = userService.createSessionDto(userEntity);
//            httpSession.setAttribute("userSessionDto", sessionDto);
            JobSeekerEntity jobSeekerEntity = jobSeekerRepository.findByUserEntity(userEntity);
            model.addAttribute("jobSeekerEntity", jobSeekerEntity);
//        }
        model.addAttribute("userSessionDto", sessionDto);
        model.addAttribute("vacancyEntityList",vacancyRepository.findAll());
        return "user_dashboard";
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
            model.addAttribute("errorMessage", "Username already exists.");
            return "register";
        }
    }

}
