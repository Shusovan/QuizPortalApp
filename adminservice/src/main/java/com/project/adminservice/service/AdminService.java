package com.project.adminservice.service;

import com.project.adminservice.exception.AdminNotFoundException;
import com.project.adminservice.model.Admin;

public interface AdminService 
{

    Admin createAdmin(Admin admin);

    Admin getAdminByEmail(String adminEmail) throws AdminNotFoundException;
    
}
