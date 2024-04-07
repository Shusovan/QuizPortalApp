package com.project.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    public User findByUserId(String userId);

    public User findByEmail(String email);
}
