package com.example.codefellowship.infrastructure;

import com.example.codefellowship.domain.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findUserByUsername(username);

        if(applicationUser==null){
            System.out.println("Username not Found");

            throw new UsernameNotFoundException((username+"Not Found"));
        }
        return applicationUser;
    }
}
