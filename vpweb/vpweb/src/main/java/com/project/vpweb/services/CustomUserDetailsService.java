package com.project.vpweb.services;

import com.project.vpweb.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserModel byLogin = userService.getUserByUsername(username);
       if(byLogin == null) {
           System.out.println("User not found");
       }
       return User.builder()
                .username(byLogin.getUserName())
                .password(byLogin.getPassword())
                .roles(byLogin.getRole())
                .build();
    }
}
