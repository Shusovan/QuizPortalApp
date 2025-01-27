package com.project.securityservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.securityservice.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByName(String name);
}
