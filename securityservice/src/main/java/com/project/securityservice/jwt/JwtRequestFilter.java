package com.project.securityservice.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.securityservice.config.UserDetailServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtRequestFilter extends OncePerRequestFilter 
{

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    
    private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException 
    {

        final String authorizationHeader = request.getHeader("Authorization");
        
        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) 
        {
            jwt = authorizationHeader.substring(7);

            try 
            {
                username = jwtUtil.extractUsername(jwt);
            } 
            
            catch (ExpiredJwtException e) 
            {
                log.warn("JWT Token has expired: {}", e.getMessage()); // Correct usage
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired");
                return; // Prevent further processing
            } 
            
            catch (Exception e) 
            {
                log.error("Unable to parse JWT Token: {}", e.getMessage()); // Correct usage
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
                return; // Prevent further processing
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) 
        {
            var userDetails = userDetailServiceImpl.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt)) 
            {
                var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                log.info("Authentication successful for user: {}", username); // Correct usage
            } 
            
            else 
            {
                log.warn("Token validation failed for user: {}", username); // Correct usage
            }
        } 
        
        else 
        {
            log.debug("Username is null or SecurityContext already contains an authentication");
        }

        chain.doFilter(request, response);
    }
}


