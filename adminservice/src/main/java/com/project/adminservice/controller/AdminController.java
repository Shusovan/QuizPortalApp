package com.project.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.adminservice.exception.AdminNotFoundException;
import com.project.adminservice.model.Admin;
import com.project.adminservice.service.AdminService;


@RestController
@RequestMapping("/admin-service/api/v1/admin")
public class AdminController 
{
    
    @Autowired
    private AdminService adminService;


    @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createAdmin(admin));
    }

    @GetMapping("/get/email")
    public ResponseEntity<Admin> getAdminByEmail(@RequestParam String adminEmail) throws AdminNotFoundException
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(adminService.getAdminByEmail(adminEmail));
    }
    
}
