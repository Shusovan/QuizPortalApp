package com.project.securityservice.dto;

import com.roles.dto.AdminDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse 
{

    private String jwtToken;
    
    private AdminDTO admin;

}
