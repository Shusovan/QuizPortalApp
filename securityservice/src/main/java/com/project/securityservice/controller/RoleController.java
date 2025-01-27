package com.project.securityservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.securityservice.model.Role;
import com.project.securityservice.repository.RoleRepository;
import com.project.securityservice.service.RoleService;
import com.roles.dto.RoleDTO;


@RestController
@RequestMapping("/security-service/api/v1/roles")
public class RoleController 
{
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/{roleName}")
    public ResponseEntity<RoleDTO> getRoleByName(@PathVariable String roleName) 
    {
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
        
        return ResponseEntity.ok(new RoleDTO(role.getId(), role.getName()));
    }
}
