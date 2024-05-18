package com.santechture.api.service;


import com.santechture.api.entity.Admin;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final AdminService adminService;

    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> userOptional = adminService.getAdminByUsername(username);
        return userOptional
                .map(user -> new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), Collections.emptyList()))
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + username));
    }


}
