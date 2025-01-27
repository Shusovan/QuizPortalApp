package com.project.securityservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.roles.dto.AdminDTO;

/*@Service
public class AdminClient 
{
    private final RestClient restClient;

    public AdminClient(RestClient.Builder builder, @Value("${admin.service.url}") String adminServiceUrl)
    {
        this.restClient = builder.baseUrl(adminServiceUrl).build();
    }

    
    public AdminDTO fetchAdminByEmail(String adminEmail) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin-service/api/v1/admin/get/email")
                        .queryParam("email", adminEmail)
                        .build())
                .retrieve()
                .body(AdminDTO.class); // Blocking call for simplicity in non-reactive contexts
    }
    
}*/

@Service
public class AdminClient {

    private final RestClient restClient;

    public AdminClient(RestClient.Builder builder, @Value("${admin.service.url}") String adminServiceUrl) 
    {
        System.out.println("AdminClient Constructor Invoked");
        System.out.println("RestClient.Builder: " + builder);
        System.out.println("Admin Service URL: " + adminServiceUrl);

        if (adminServiceUrl == null || adminServiceUrl.isBlank()) {
            throw new IllegalArgumentException("Admin service URL is not configured or is empty");
        }

        try 
        {
            this.restClient = builder.baseUrl(adminServiceUrl).build();
            System.out.println("RestClient successfully initialized: " + restClient);
        } 
        
        catch (Exception e) 
        {
            System.out.println("Exception during RestClient initialization: " + e.getMessage());
            throw new IllegalStateException("Failed to initialize AdminClient due to RestClient setup error", e);
        }
    }

    public AdminDTO fetchAdminByEmail(String adminEmail) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin-service/api/v1/admin/get/email")
                        .queryParam("adminEmail", adminEmail)
                        .build())
                .retrieve()
                .body(AdminDTO.class);
    }
}


