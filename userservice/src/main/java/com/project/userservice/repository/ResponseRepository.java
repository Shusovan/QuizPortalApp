package com.project.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.userservice.model.UserResponse;

public interface ResponseRepository extends JpaRepository<UserResponse, Long>
{
    
}
