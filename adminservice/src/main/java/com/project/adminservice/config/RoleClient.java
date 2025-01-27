package com.project.adminservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.roles.dto.RoleDTO;

@Service
public class RoleClient 
{

    private final RestClient restClient;

    @Autowired
    public RoleClient(RestClient.Builder restClientBuilder, @Value("${security.service.url}") String securityServiceUrl) 
    {
        if (!securityServiceUrl.startsWith("http://") && !securityServiceUrl.startsWith("https://")) 
        {
            throw new IllegalArgumentException("Invalid Base URL: " + securityServiceUrl);
        }

        this.restClient = restClientBuilder
                .baseUrl(securityServiceUrl + "/api/v1/roles")
                .build();
    }

    public RoleDTO fetchRoleByName(String roleName) 
    {
        return restClient.get()
                .uri("/{roleName}", roleName)
                .retrieve()
                .body(RoleDTO.class);
    }

}
