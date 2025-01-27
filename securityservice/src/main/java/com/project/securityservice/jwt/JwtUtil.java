package com.project.securityservice.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil 
{

    private final String SECRET_KEY = "JW8KqzuIBb8J7BsiYGnQq7rx3WIiskV0O6eNjeTzwZo=";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    private final long TOKEN_VALIDITY = 1000 * 60 * 60 * 10; // 10 hours


    // Generate token with username and roles
    public String generateToken(String username, Set<String> roles) 
    {
        return Jwts.builder()
                .setSubject(username) // Subject is the username
                .claim("roles", roles) // Include roles as a claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate the token
    public boolean validateToken(String token) 
    {
        try 
        {
            getClaims(token); // Will throw an exception if the token is invalid
            return true;
        } 
        
        catch (Exception e) 
        {
            return false;
        }
    }

    // Extract the username from the token
    public String extractUsername(String token) 
    {
        return getClaims(token).getSubject(); // Subject contains the username
    }

    // Extract roles from the token
    @SuppressWarnings("unchecked")
    public Set<String> getRolesFromToken(String token) 
    {
        Claims claims = getClaims(token);
        List<String> rolesList = claims.get("roles", List.class); // Retrieve roles as a List
        return new HashSet<>(rolesList); // Convert to a Set for consistency
    }

    // Check if the token is expired
    public boolean isTokenExpired(String token) 
    {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Parse the claims from the token
    public Claims getClaims(String token) 
    {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


