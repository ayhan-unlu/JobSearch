package com.ayhanunlu.security;

import com.ayhanunlu.service.CustomAuthenticationProvider;
import com.ayhanunlu.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
/*
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;
*/

/*
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(customUserDetailsService);
//        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

*/

/*
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(customUserDetailsService);
        //  daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }
*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

/*
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/images/**").permitAll()
                        .requestMatchers("/admin_dashboard").hasRole("ADMIN")
                        .anyRequest().authenticated())
//                .authenticationProvider(authenticationProvider())
                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/admin_dashboard",true)
//                        .permitAll())
                        .disable()
                        .logout(logout -> logout.permitAll()));
*/

        httpSecurity
                .authenticationProvider(authenticationProvider());

        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/css/**", "/images/**").permitAll()
                        .requestMatchers("/admin_dashboard").hasRole("ADMIN")
                        .requestMatchers("/user_dashboard").hasRole("USER")
                        .anyRequest().authenticated()
                );
        httpSecurity
                .formLogin(form -> form
                                .loginPage("/login")
//                        .defaultSuccessUrl("/admin_dashboard", true)
//                        .defaultSuccessUrl("/user_dashboard",true)
                                .successHandler((request, response, authentication) -> {
                                    String role = authentication.getAuthorities()
                                            .iterator().next().getAuthority();
                                    if (role.equals("ROLE_ADMIN")) {
                                        response.sendRedirect("/admin_dashboard");
                                    } else {
                                        response.sendRedirect("/user_dashboard");
                                    }
                                })
                                .permitAll()
                );
        httpSecurity
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll());
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return customAuthenticationProvider;
    }

}
