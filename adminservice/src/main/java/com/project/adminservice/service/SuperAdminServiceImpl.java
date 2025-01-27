package com.project.adminservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.adminservice.model.Admin;
import com.project.adminservice.repository.AdminRepository;
import com.roles.dto.AdminDTO;


@Service
public class SuperAdminServiceImpl implements SuperAdminService
{

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public List<AdminDTO> getAllAdmins() 
    {
        // Fetch all admins from the database
        List<Admin> admins = adminRepository.findAll();

        // Map the Admin entity to AdminDTO
        return admins.stream()
                .map(admin -> new AdminDTO(
                        admin.getAdminId(),
                        admin.getAdminName(),
                        admin.getAdminEmail(),
                        admin.getPassword(),
                        admin.getCreatedAt(),
                        admin.getUpdatedAt(),
                        admin.getIsActive(),
                        admin.getIsLocked(),
                        admin.getIsDeleted(),
                        admin.getRoles()))
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO createAdmin(AdminDTO adminDTO) 
    {
        Admin admin = new Admin();

        // Save the admin in the database
        Admin savedAdmin = adminRepository.save(admin);

        // Return the saved admin as DTO
        return new AdminDTO(
            savedAdmin.getAdminId(),
            savedAdmin.getAdminName(),
            savedAdmin.getAdminEmail(),
            savedAdmin.getPassword(),
            savedAdmin.getCreatedAt(),
            savedAdmin.getUpdatedAt(),
            savedAdmin.getIsActive(),
            savedAdmin.getIsLocked(),
            savedAdmin.getIsDeleted(),
            savedAdmin.getRoles());
    }

    /*
     * @Override
    public List<AdminDTO> getAllAdmins() {
        // Fetch all admins from the database
        List<Admin> admins = adminRepository.findAll();

        // Map the Admin entity to AdminDTO
        return admins.stream()
                .map(admin -> new AdminDTO(
                        admin.getId(),
                        admin.getEmail(),
                        admin.getPassword(),
                        admin.getRoles()))
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        // Map the DTO to Entity
        Admin admin = new Admin();
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword()); // Ensure you encode the password
        admin.setRoles(adminDTO.getRoles());

        // Save the admin in the database
        Admin savedAdmin = adminRepository.save(admin);

        // Return the saved admin as DTO
        return new AdminDTO(
                savedAdmin.getId(),
                savedAdmin.getEmail(),
                savedAdmin.getPassword(),
                savedAdmin.getRoles());
    }
     */
    
}
