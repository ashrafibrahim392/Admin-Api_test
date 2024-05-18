package com.santechture.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Token {

    private Integer adminId;
    private String username;
    private String token;
}
