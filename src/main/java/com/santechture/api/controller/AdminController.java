package com.santechture.api.controller;


import com.santechture.api.dto.GeneralResponse;
import com.santechture.api.service.AdminService;
import com.santechture.api.service.CustomUserDetailsService;
import com.santechture.api.util.JwtTokenUtil;
import com.santechture.api.validation.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "admin")
public class AdminController {

    private final AdminService adminService;
    private final AuthenticationManager authenticationManager;


    @PostMapping ("/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody LoginRequest request) throws Exception {
        authenticate(request.getUsername(),request.getPassword());

        return new GeneralResponse().response(adminService.login(request));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
