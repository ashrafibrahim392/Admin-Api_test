package com.santechture.api.controller;


import com.santechture.api.dto.GeneralResponse;
import com.santechture.api.exception.BusinessExceptions;
import com.santechture.api.service.UserService;
import com.santechture.api.util.JwtTokenUtil;
import com.santechture.api.validation.AddUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;



    @GetMapping
    public ResponseEntity<GeneralResponse> list(Pageable pageable){
        return userService.list(pageable);
    }
    @PostMapping
    public ResponseEntity<GeneralResponse> addNewUser(@RequestBody AddUserRequest request) throws BusinessExceptions {
     String token =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    int adminId = jwtTokenUtil.getClaimFromToken(token.substring(7),claim ->  claim.get("adminId" ,Integer.class) );

        return userService.addNewUser(request, adminId);
    }
}
