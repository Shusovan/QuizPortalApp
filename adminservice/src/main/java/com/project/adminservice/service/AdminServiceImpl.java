package com.project.adminservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.adminservice.config.RoleClient;
import com.project.adminservice.exception.AdminNotFoundException;
import com.project.adminservice.model.Admin;
import com.project.adminservice.repository.AdminRepository;
import com.roles.dto.RoleDTO;


@Service
public class AdminServiceImpl implements AdminService
{

    private final AdminRepository adminRepository;

    private final RoleClient roleClient;

    // Constructor Injection
    public AdminServiceImpl(AdminRepository adminRepository, RoleClient roleClient) 
    {
        this.adminRepository = adminRepository;
        this.roleClient = roleClient;
    }


    @Override
    public Admin createAdmin(Admin admin) 
    {
        return adminRepository.save(admin);
    }


    @Override
    public Admin getAdminByEmail(String adminEmail) throws AdminNotFoundException 
    {
        Admin admin = adminRepository.findByAdminEmail(adminEmail).orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));

        return admin;
    } 
    
}
