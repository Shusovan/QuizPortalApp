package com.roles.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class AdminDTO 
{

    private String adminId;

    private String adminName;

    private String adminEmail;

    private String password;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    private Boolean isActive = true;

    private Boolean isLocked = false;

    private Boolean isDeleted = false;

    private Set<RoleDTO> roles = new HashSet<>();


    public AdminDTO() 
    {

    }


    public AdminDTO(String adminId, String adminName, String adminEmail, String password, LocalDateTime createdAt,
            LocalDateTime updatedAt, Boolean isActive, Boolean isLocked, Boolean isDeleted, Set<RoleDTO> roles) 
    {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
        this.isLocked = isLocked;
        this.isDeleted = isDeleted;
        this.roles = roles;
    }


    public String getAdminId() 
    {
        return adminId;
    }

    public void setAdminId(String adminId) 
    {
        this.adminId = adminId;
    }

    public String getAdminName() 
    {
        return adminName;
    }

    public void setAdminName(String adminName) 
    {
        this.adminName = adminName;
    }

    public String getAdminEmail() 
    {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) 
    {
        this.adminEmail = adminEmail;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() 
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) 
    {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() 
    {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Boolean getIsActive() 
    {
        return isActive;
    }

    public void setIsActive(Boolean isActive) 
    {
        this.isActive = isActive;
    }

    public Boolean getIsLocked() 
    {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) 
    {
        this.isLocked = isLocked;
    }

    public Boolean getIsDeleted() 
    {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Set<RoleDTO> getRoles() 
    {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) 
    {
        this.roles = roles;
    }

}
