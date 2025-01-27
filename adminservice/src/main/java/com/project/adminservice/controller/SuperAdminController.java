package com.project.adminservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.adminservice.config.SecurityClient;
import com.project.adminservice.service.SuperAdminService;
import com.roles.dto.AdminDTO;
import com.roles.dto.TokenValidationResponse;


@RestController
@RequestMapping("/admin-service/api/v1/superadmin")
public class SuperAdminController 
{

    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private SecurityClient securityClient;


    private static final Logger logger = LoggerFactory.getLogger(SuperAdminController.class);


    @GetMapping("/get-all-admins")
    public ResponseEntity<List<AdminDTO>> getAllAdmins(@RequestHeader("Authorization") String token) 
    {
        // Log token received
        logger.info("Token received: {}", token);

        TokenValidationResponse validationResponse = securityClient.validateToken(token);
        
        // Log token validation response
        logger.info("Token Validation Response: {}", validationResponse);
        logger.info("Roles received: {}", validationResponse.getRoles());

        // Check if the user has the SUPER_ADMIN role
        if (!validationResponse.getRoles().stream().anyMatch(role -> role.equals("ROLE_SUPER_ADMIN"))) 
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        return ResponseEntity.ok(superAdminService.getAllAdmins());
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestHeader("Authorization") String token, @RequestBody AdminDTO adminDTO) 
    {
        TokenValidationResponse validationResponse = securityClient.validateToken(token);
        System.out.println("Token Validation Response: " + validationResponse);
        System.out.println("Roles received: " + validationResponse.getRoles());

        // Check if the user has the SUPER_ADMIN role
        if (!validationResponse.getRoles().contains("ROLE_SUPER_ADMIN")) 
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        return ResponseEntity.ok(superAdminService.createAdmin(adminDTO));
    }
}