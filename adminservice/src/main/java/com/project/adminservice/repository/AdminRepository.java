package com.project.adminservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.adminservice.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, String>
{
    Optional<Admin> findByAdminEmail(String adminEmail);
}
