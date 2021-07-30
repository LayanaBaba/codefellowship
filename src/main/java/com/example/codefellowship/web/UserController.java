package com.example.codefellowship.web;

import com.example.codefellowship.domain.ApplicationUser;
import com.example.codefellowship.infrastructure.ApplicationUserRepository;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signUp";
    }

    @PostMapping("/signup")
    public RedirectView postSignUp( @RequestParam String username,
                                    @RequestParam String password,
                                    @RequestParam String firstName,
                                    @RequestParam String lastName,
                                    @RequestParam String dateOfBirth,
                                    @RequestParam String bio){
//        password= PasswordEncoder.encode(password);
        ApplicationUser applicationUser = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
//       applicationUser= applicationUserRepository.save(applicationUser);
        applicationUserRepository.save(applicationUser);

        //        Authentication authentication = new UsernamePasswordAuthenticationToken(applicationUser, null, applicationUser.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLogInPage(){
        return "logIn";
    }

    @GetMapping("/profile")
    public String getProfileInfo(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ApplicationUser applicationUser = applicationUserRepository.findUserByUsername(userDetails.getUsername());
        model.addAttribute("user", userDetails.getUsername());
        return "profile";
    }

    @GetMapping("/users/{id}")
    public String getUserData(@PathVariable Long id, Model model){
        ApplicationUser applicationUser = applicationUserRepository.getById(id);
        model.addAttribute("user", applicationUser);
        return "users";
    }

}
