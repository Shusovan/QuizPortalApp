package com.project.adminservice.service;

import java.util.List;

import com.project.adminservice.model.Admin;
import com.roles.dto.AdminDTO;

public interface SuperAdminService 
{

    List<AdminDTO> getAllAdmins();

    AdminDTO createAdmin(AdminDTO adminDTO);
    
}
