package com.project.adminservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.roles.dto.TokenValidationResponse;

@Service
public class SecurityClient 
{
    @Autowired
    private RestTemplate restTemplate;

    @Value("${security.service.url}")
    private String securityServiceUrl;

    public TokenValidationResponse validateToken(String token) 
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        String url = securityServiceUrl + "/api/v1/public/validate-token";
        return restTemplate.exchange(url, HttpMethod.POST, request, TokenValidationResponse.class).getBody();
    }
}
