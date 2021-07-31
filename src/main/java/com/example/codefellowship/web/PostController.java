package com.example.codefellowship.web;

import com.example.codefellowship.domain.ApplicationUser;
import com.example.codefellowship.domain.Post;
import com.example.codefellowship.infrastructure.ApplicationUserRepository;
import com.example.codefellowship.infrastructure.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class PostController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/newpost")
    public RedirectView postNewPost(@RequestParam String body){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(format);

        Post post = new Post(body, formatDateTime);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setApplicationUser(applicationUserRepository.findUserByUsername(userDetails.getUsername()));

        postRepository.save(post);

        return new RedirectView("/profile");
    }
}
