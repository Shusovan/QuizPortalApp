package com.project.securityservice.service;

import com.project.securityservice.exception.RoleNotFoundException;
import com.project.securityservice.model.Role;

public interface  RoleService 
{

    Role getRoles(String name) throws RoleNotFoundException;
    
}
