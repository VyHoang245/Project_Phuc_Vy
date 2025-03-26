package com.project.vpweb.services;

import com.project.vpweb.models.CustomUserDetails;
import com.project.vpweb.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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
//       return User.builder()
//                .username(byLogin.getUserName())
//                .password(byLogin.getPassword())
//               .authorities(byLogin.getRoles().stream()
//                       .map(role -> new SimpleGrantedAuthority(role.getName())) // Convert roles to GrantedAuthority
//                       .collect(Collectors.toList()))
//                .build();
        return new CustomUserDetails(
                byLogin.getId(),  // Store ID
                byLogin.getUserName(),
                byLogin.getPassword(),
                byLogin.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())) // Convert roles to GrantedAuthority
                        .collect(Collectors.toList())
        );
    }
}
