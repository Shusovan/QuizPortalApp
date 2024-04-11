package com.project.userservice.model;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity(name = "Users")
@Data

@Table(name = "Users")
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userAutoIncrementId")
    private Long userAutoIncrementId;

    @Column(name = "userId")
    private String userId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    @Email(message = "Email is invalid")
    private String email;

    transient private List<Quiz> quiz;
}