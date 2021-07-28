package com.example.codefellowship.web;

import com.example.codefellowship.domain.ApplicationUser;
import com.example.codefellowship.infrastructure.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.ui.Model;



@Controller
public class UserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signUp";
    }

    @PostMapping("/signup")
    public RedirectView postSignUp(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam String dateOfBirth,
                                   @RequestParam String bio){
        ApplicationUser applicationUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName, dateOfBirth, bio);
       applicationUser= applicationUserRepository.save(applicationUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(applicationUser, null, applicationUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLogInpPage(){
        return "logIn";
    }

    @GetMapping("/profile")
    public String getProfileInfo(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ApplicationUser applicationUser = applicationUserRepository.findUserByUsername(userDetails.getUsername());
        model.addAttribute("username", userDetails.getUsername());
        return "profile";
    }

}
