package com.example.codefellowship.infrastructure.services;

import com.example.codefellowship.domain.ApplicationUser;
import com.example.codefellowship.infrastructure.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ApplicationUserService")
public class ApplicationUserImp implements ApplicationUserService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    public List<ApplicationUser> findAllUsers() {
        return applicationUserRepository.findAll();
    }
}
