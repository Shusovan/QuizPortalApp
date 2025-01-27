package com.project.securityservice.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.securityservice.config.AdminClient;
import com.project.securityservice.dto.AuthRequest;
import com.project.securityservice.dto.AuthResponse;
import com.project.securityservice.jwt.JwtUtil;
import com.roles.dto.AdminDTO;
import com.roles.dto.TokenValidationResponse;

import io.jsonwebtoken.Claims;


@RestController
@RequestMapping("/security-service/api/v1/public")
public class AuthController 
{

    @Autowired
    private AuthenticationManager authenticationManager; // generateToken(userDetails)

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminClient adminClient;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) 
    {
        try 
        {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getAdminEmail(), authRequest.getPassword()));

            // Get authenticated user's details
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Extract username and roles
            String username = userDetails.getUsername();

            Set<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());

            // Generate JWT token
            String token = jwtUtil.generateToken(username, roles);

            // Fetch admin details from AdminService
            AdminDTO admin = adminClient.fetchAdminByEmail(authRequest.getAdminEmail());

            // Return response with token and admin info
            return ResponseEntity.ok(new AuthResponse(token, admin));
        } 
    
        catch (AuthenticationException e) 
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }



    @PostMapping("/validate-token")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestHeader("Authorization") String token) 
    {
        if (token == null || !token.startsWith("Bearer ")) 
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or missing token");
        }

        String jwt = token.substring(7); // Remove "Bearer " prefix

        if(!jwtUtil.validateToken(jwt)) 
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        // Log the token claims for debugging
        Claims claims = jwtUtil.getClaims(jwt);
        System.out.println("Token Claims: " + claims); // Add this line for debugging

        String username = jwtUtil.extractUsername(jwt);
        Set<String> roles = jwtUtil.getRolesFromToken(jwt); // A method to extract roles

        return ResponseEntity.ok(new TokenValidationResponse(username, roles));
    }

}
