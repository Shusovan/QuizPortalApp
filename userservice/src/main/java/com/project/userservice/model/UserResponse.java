package com.project.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data

@Table(name = "UserResponse")
public class UserResponse 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userQuestionAutoID;    
    
    private UserQuiz userQuiz;

    private Long questionId;

    private String userResponse;
    
}
