package com.project.adminservice.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.adminservice.model.Admin;
import com.project.adminservice.repository.AdminRepository;
import com.roles.dto.RoleDTO;

@Component
public class SuperAdminDataInitializer implements CommandLineRunner
{

    private final AdminRepository adminRepository;
    
    private final RoleClient roleClient;
    
    private final PasswordEncoder passwordEncoder;

    public SuperAdminDataInitializer(AdminRepository adminRepository, RoleClient roleClient, PasswordEncoder passwordEncoder) 
    {
        this.adminRepository = adminRepository;
        this.roleClient = roleClient;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) 
    {
        // Fetch SUPER_ADMIN Role ID from SecurityService
        RoleDTO superAdminRole = roleClient.fetchRoleByName("SUPER_ADMIN");

        // Check if SUPER_ADMIN exists in AdminService; if not, create it
        adminRepository.findByAdminEmail("superadmin@email.com").orElseGet(() -> {
            
            Admin superAdmin = new Admin();
            
            superAdmin.setAdminName("superadmin");
            superAdmin.setAdminEmail("superadmin@email.com");
            superAdmin.setPassword(passwordEncoder.encode("superadmin")); // Use a secure password
            superAdmin.setRoles(Set.of(superAdminRole));
            
            //Set<RoleDTO> roles = new HashSet<>();
            //roles.add(superAdminRole);
            //superAdmin.setRoles(roles);
            
            return adminRepository.save(superAdmin);
        });

        System.out.println("SUPER_ADMIN pre-populated successfully.");
    }
}
