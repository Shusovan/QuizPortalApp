package com.project.securityservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.securityservice.model.Role;
import com.project.securityservice.repository.RoleRepository;

@Configuration
public class RoleDataInitializer implements CommandLineRunner
{

    private final RoleRepository roleRepository;

    public RoleDataInitializer(RoleRepository roleRepository) 
    {
        this.roleRepository = roleRepository;
    }

    
    @Override
    public void run(String... args) 
    {
        roleRepository.findByName("SUPER_ADMIN").orElseGet(() -> roleRepository.save(new Role("SUPER_ADMIN")));
        roleRepository.findByName("ADMIN").orElseGet(() -> roleRepository.save(new Role("ADMIN")));

        System.out.println("Roles pre-populated successfully.");
    }
}
