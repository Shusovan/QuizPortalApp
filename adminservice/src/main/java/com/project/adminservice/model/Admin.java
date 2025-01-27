package com.project.adminservice.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.roles.dto.RoleDTO;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Admin 
{

  @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "admin_name", nullable = false)
    private String adminName;

    @Column(name="admin_email", nullable = false, unique = true)
    private String adminEmail;

    @Column(name="password", nullable = false, unique = true)
    private String password;

    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "admin_roles", 
      joinColumns = @JoinColumn(name = "admin_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;*/

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private Boolean isLocked = false;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @ElementCollection
    @CollectionTable(name = "admin_roles", joinColumns = @JoinColumn(name = "admin_id"))
    private Set<RoleDTO> roles = new HashSet<>();
    
}
