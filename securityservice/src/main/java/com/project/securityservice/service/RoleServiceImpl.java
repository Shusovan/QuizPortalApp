package com.project.securityservice.service;

import org.springframework.stereotype.Service;

import com.project.securityservice.exception.RoleNotFoundException;
import com.project.securityservice.model.Role;
import com.project.securityservice.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService
{

    private final RoleRepository roleRepository;

    // Contructor Injection
    public RoleServiceImpl(RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role getRoles(String name) throws RoleNotFoundException
    {
        Role role = roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException("ROLE_NOT_FOUND"));

        return role;
    }

}
