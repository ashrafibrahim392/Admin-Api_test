package com.santechture.api.service;

import com.santechture.api.dto.GeneralResponse;
import com.santechture.api.dto.Token;
import com.santechture.api.dto.admin.AdminDto;
import com.santechture.api.entity.Admin;
import com.santechture.api.exception.BusinessExceptions;
import com.santechture.api.repository.AdminRepository;
import com.santechture.api.util.InMemoryForToken;
import com.santechture.api.util.JwtTokenUtil;
import com.santechture.api.validation.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {


    private final AdminRepository adminRepository;
    private final JwtTokenUtil jwtUtil;

    public ResponseEntity<GeneralResponse> login(LoginRequest request) throws BusinessExceptions {
        Optional<Admin> adminOpt = adminRepository.findByUsernameIgnoreCase(request.getUsername());

      Token token =   adminOpt.map(admin -> Token.builder().adminId(admin.getAdminId()).username(admin.getUsername())
              .token(jwtUtil.generateToken(admin)).build()).orElse(null);

        InMemoryForToken.addNewToken(token);

        return new GeneralResponse().response(token);
    }

    public Optional<Admin> getAdminByUsername (String user){
        return adminRepository.findByUsernameIgnoreCase(user);
    }

}
